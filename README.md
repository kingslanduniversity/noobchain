# NoobChain

A simplified blockchain implementation in Java built for educational purposes. Learn core blockchain concepts — blocks, transactions, proof-of-work mining, and chain validation — through an interactive command-line interface.

## Features

- **SHA-256 Proof-of-Work Mining** — find valid block hashes with adjustable difficulty
- **Transaction Pool** — add pending transactions and mine them into blocks
- **Chain Validation** — blocks are verified before being added to the chain
- **Adjustable Difficulty** — increase or decrease the number of leading zeros required in block hashes
- **Interactive CLI** — explore the blockchain through simple text commands

## Project Structure

```
src/com/kingsland/
├── blockchain/              # Core blockchain logic
│   ├── Block.java           # Immutable block with transactions, nonce, and hashes
│   ├── BlockCandidate.java  # Template for a block to be mined
│   ├── Blockchain.java      # Chain management, validation, and mining jobs
│   ├── Transaction.java     # Sender/recipient/amount data
│   └── Utils.java           # SHA-256 hashing and proof-of-work utilities
└── client/                  # Command-line interface
    ├── Main.java            # REPL loop with dynamic command loading
    ├── annotations/
    │   └── Command.java     # Annotation for registering commands
    └── commands/            # Individual command implementations
```

## Getting Started

### Prerequisites

- Java 8 or higher
- IntelliJ IDEA (recommended) or any Java compiler

### Build & Run

**With IntelliJ IDEA:**

1. Open the project directory in IntelliJ
2. Build the project (`Build → Build Project`)
3. Run `com.kingsland.client.Main`

**With the command line:**

```
javac -d out -sourcepath src src/com/kingsland/client/Main.java
java -cp out com.kingsland.client.Main
```

## Usage

Once running, you'll see an interactive prompt. Type `help` to see all available commands:

| Command | Description |
|---|---|
| `add pending` | Create a new transaction (sender, recipient, amount) |
| `mine` | Mine pending transactions into a new block |
| `show blocks` | Display all blocks in the chain |
| `show pending` | List unconfirmed transactions |
| `show difficulty` | Display current mining difficulty |
| `increase difficulty` | Make mining harder (more leading zeros) |
| `decrease difficulty` | Make mining easier (fewer leading zeros) |
| `reset` | Clear the chain and start fresh |
| `help` | List all commands |
| `exit` | Exit the application |

### Example Session

```
> add pending
Sender: Alice
Recipient: Bob
Amount: 50
Pending transaction added successfully

> mine
Mining block...
Mined block for 0.45s
BLOCK_HASH: 0000abc123...

> show blocks
--------- BLOCK 0 ---------
transactions:
nonce: 0
prv_blockhash: GEN
blockhash: GEN

--------- BLOCK 1 ---------
transactions:
	{sender: Alice, recipient: Bob, amount: 50}
nonce: 12547
prv_blockhash: GEN
blockhash: 0000abc123...
```

## How It Works

1. **Transactions** are created and held in a pending pool
2. **Mining** pulls pending transactions into a block candidate, then brute-forces a nonce until the SHA-256 hash of `previousBlockHash + transactions + nonce` starts with the required number of zeros
3. **Validation** checks that the block's previous hash matches the chain, the hash is correctly computed, and it meets the difficulty requirement
4. **The block** is appended to the chain and the pending pool is cleared

The default difficulty is 4 (the hash must start with `0000`). Higher difficulty means exponentially more work to find a valid nonce.

## License

This project is intended for educational use at Kingsland University.
