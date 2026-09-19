# AGENTS.md

Instructions for AI coding agents (Claude, Copilot, Cursor, etc.) working in this repository. Human contributors may also find this useful, but it is written for agents.

## Project

**CharlZK Password Manager** — a local-only, offline Windows desktop password manager written in Java. No network activity, no cloud sync, no master password recovery (by design). MIT licensed.

- Language: Java
- Architecture: MVC
- UI: Java Swing + FlatLaf (theming) + flatlaf-extras (`FlatSVGIcon`)
- Storage: SQLite via `xerial/sqlite-jdbc`, encrypted at rest with SQLCipher
- Build: Maven
- Root package: `org.charlzk`
- Testing: none yet — no framework is set up. Do not assume JUnit is present; do not add a test dependency unless explicitly asked.
- CI/CD: none yet
- Target platform: Windows only
- Database schema and design: see [`DATABASE.md`](./references/DATABASE.md)

## STRICT RULES

These are non-negotiable and override any conflicting instinct or default behavior:

1. **Do not over-engineer when generating new code.** Solve exactly the problem asked. No speculative abstractions, no "just in case" flexibility, no extra layers of indirection that weren't requested.
2. **When refactoring, STRICTLY do not modify existing human-written code.** If a refactor is requested, only touch what is explicitly targeted. Surrounding human code — including its structure, naming, and style — stays exactly as-is.
3. **When generating new code, STRICTLY follow the structure of the existing human code.** New code must match established conventions (naming, error handling via `throws`, try-with-resources usage, formatting) rather than introducing a different style or pattern.

## Golden rule: minimal-change discipline

This is the single most important rule for this repo.

- Fix or add only what was asked. Do not refactor, rename, reformat, or "improve" surrounding code that wasn't part of the request.
- Preserve existing method signatures, variable names, class structure, and formatting style exactly, unless the task explicitly requires changing them.
- Never delete existing code to "clean up" unless told to.
- If you spot a bug or design flaw outside the scope of the current task, **point it out and ask** — do not silently fix it or silently leave it.
- New code should match the surrounding code's existing style and patterns, not introduce a new idiom.

## Architecture rules

- **MVC boundaries**: Controllers handle logic and call DAOs. Views (Swing components) do not talk to the database directly. Models are plain data holders.
- **DAO layer** (`UserDAO`, `FolderDAO`, `PasswordEntryDAO`, etc.):
  - Methods declare `throws SQLException` (and `IOException` where relevant). Do not wrap DB errors in try-catch-and-show-dialog inside the DAO — that's the caller's (controller/view's) job.
  - Never return a live `ResultSet` from a DAO method. Always map rows to a disconnected model object before the method returns / connection scope closes.
  - Use try-with-resources for `PreparedStatement` and `ResultSet` only.
- **Connection lifecycle**: `SessionManager` is a singleton holding a single shared `Connection`, opened once at login after `PRAGMA key` is applied. **DAOs must never close the shared connection.** Only statements/result sets are closed locally.
- **Multi-user model is an open question.** The current `Users` table with `password_hash` implies a shared-database model, but SQLCipher encrypts at the file level — true per-user isolation likely needs separate vault files per user via `VaultRegistry`. If a task touches user/vault logic, flag this tension rather than assuming a direction.

## Security rules (non-negotiable)

- Passwords are `char[]`, never `String`, in memory. `String` is immutable and can't be zeroed out, so it lingers in memory/heap dumps.
- Table/UI display of passwords is masked; decrypt-on-demand only, never eagerly.
- Login/auth error messages must be enumeration-safe — never reveal whether the email exists vs. the password was wrong. Same generic message either way.
- No plaintext secrets in logs, exceptions surfaced to UI, or committed code.
- `INSERT OR IGNORE` requires a schema-level `UNIQUE` constraint to actually work — don't rely on Java-side duplicate checks alone.

## Known Swing/FlatLaf gotchas (don't relearn these)

- `TableRowSorter`: always call `convertRowIndexToModel()` before using a selected row index for data access — view/model indices diverge after sort/filter. Apply this defensively even if no sorter is currently attached to that table.
- FlatLaf's SVG renderer cannot resolve `stroke="currentColor"` (common in Lucide/Feather icon sets). Icons must use explicit hex color values.
- `BorderLayout` allows exactly one component per region — adding a second silently replaces the first with no error/warning.
- `JTextArea.getText()` does not insert `\n` for wrapped lines — word wrap is visual only, doesn't touch the underlying document content.

## Data conventions

- Timestamps: standardize on **epoch milliseconds** (`Folders.created_at` convention), not ISO-8601 strings (`Passwords.date_updated` currently does this inconsistently — known tech debt, don't "fix" it opportunistically unless asked).
- `java.time` (`Instant`, `ZoneId`, `DateTimeFormatter`) is the standard for any date formatting/conversion.

## When asked to write or modify code

1. Ask clarifying questions first if the request has ambiguity around: scale/data volume, security implications, how it affects the connection/session lifecycle, or how it interacts with the multi-user/vault question above.
2. Keep responses concise — targeted code snippets over full-file rewrites unless a full file is what's needed.
3. Point out design flaws, anti-patterns, or things that won't scale, even if not asked — but keep the actual code change scoped to the request.
4. Validation pattern to follow: a private `boolean validateForm()`-style method, separated from action/submit logic, with inputs trimmed before validation.

## Do not

- Do not add a testing framework, CI config, or build plugin unless explicitly requested.
- Do not introduce network calls of any kind — this app is offline by design.
- Do not change the master-password-recovery-doesn't-exist design; that's intentional.
- Do not restructure MVC layers, package names, or file locations as a "improvement" without being asked.