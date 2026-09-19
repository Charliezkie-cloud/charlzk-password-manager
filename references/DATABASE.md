# DATABASE.md

Database design reference for Charlzk Password Manager. Source of truth is `DatabaseInitializer.java` — if this doc and the code ever disagree, the code wins; update this doc.

## Engine

- SQLite, encrypted at rest via SQLCipher (`PRAGMA key` applied on connect, see `DatabaseConnection`)
- Foreign key enforcement: `PRAGMA foreign_keys = ON` is set in `DatabaseConnection` (SQLite has this off by default per-connection — it must be set on every new connection, not just once globally)
- Schema is created idempotently via `CREATE TABLE IF NOT EXISTS`, gated by `DatabaseInitializer.isDatabaseInitialized()`, which checks for the `Users` table's existence in `sqlite_master`

## Schema

### Users

| Column | Type | Constraints |
|---|---|---|
| user_id | INTEGER | PRIMARY KEY AUTOINCREMENT |
| email | TEXT | NOT NULL, UNIQUE |
| username | TEXT | NOT NULL |
| password_hash | TEXT | NOT NULL |
| created_at | INTEGER | NOT NULL |
| updated_at | INTEGER | |

- Single shared database, not per-user vault files. `email` is the unique login identifier.
- Timestamps are epoch milliseconds (`INTEGER`).

### Folders

| Column | Type | Constraints |
|---|---|---|
| folder_id | INTEGER | PRIMARY KEY AUTOINCREMENT |
| user_id | INTEGER | NOT NULL, FK → Users(user_id) ON DELETE CASCADE |
| name | TEXT | NOT NULL |
| created_at | INTEGER | NOT NULL |

- `UNIQUE (user_id, name)` — a user cannot have two folders with the same name; names can repeat across different users.
- Deleting a user cascades and deletes all their folders.

### PasswordEntries

| Column | Type | Constraints |
|---|---|---|
| entry_id | INTEGER | PRIMARY KEY AUTOINCREMENT |
| folder_id | INTEGER | FK → Folders(folder_id) ON DELETE SET NULL |
| user_id | INTEGER | NOT NULL, FK → Users(user_id) ON DELETE CASCADE |
| title | TEXT | NOT NULL |
| username | TEXT | |
| password | TEXT | |
| url | TEXT | |
| note | TEXT | |
| created_at | INTEGER | NOT NULL |
| updated_at | INTEGER | |

- `folder_id` is nullable — deleting a folder does **not** delete its entries, it orphans them (sets `folder_id` to `NULL`, i.e. "unfiled").
- Deleting a user cascades and deletes all their password entries.
- `password` is stored as `TEXT` in this table — encryption happens at the file level via SQLCipher, not per-column. See engineering notes: in-memory handling must still use `char[]`, never `String`, on the Java side regardless of how it's stored.

## Relationships

```
Users (1) ───< (many) Folders
Users (1) ───< (many) PasswordEntries
Folders (1) ─┬─< (many) PasswordEntries   [nullable — entry survives folder deletion]
```

## Known gaps / open questions

- `isDatabaseInitialized()` opens its own `Connection` via `DatabaseConnection.getConnection()` instead of reusing the caller's connection, and does not close it. Confirmed intentional for now (connection is only used to check schema existence) — flagged here as a known leak, not to be silently fixed.
- Multi-user model is a shared database with `password_hash`, not per-user SQLCipher vault files. True per-user data isolation at the file level is still an open architectural question (see project overview).
- No `DROP`/migration strategy defined yet — schema changes currently rely on manually editing `CREATE TABLE IF NOT EXISTS` statements, which will not alter existing tables once created.

---

# STRICT RULES

These are non-negotiable and apply to every code-related task in this repository, for both AI agents and human contributors following the same discipline.

1. **Do not over-engineer when generating new code.** Solve exactly the problem asked. No speculative abstractions, no "just in case" flexibility, no extra layers of indirection that weren't requested.
2. **When refactoring, STRICTLY do not modify existing human-written code.** If a refactor is requested, only touch what is explicitly targeted. Surrounding human code — including its structure, naming, and style — stays exactly as-is.
3. **When generating new code, STRICTLY follow the structure of the existing human code.** New code must match established conventions (naming, error handling via `throws`, try-with-resources usage, formatting) rather than introducing a different style or pattern.