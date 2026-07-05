# Owner AFK Plugin

A Minecraft Spigot plugin that displays a sidebar message to all players when the server owner is in spectator mode (AFK).

## Features

- Monitors the owner's game mode
- Displays a customizable sidebar when owner enters spectator mode
- Automatically removes the sidebar when owner leaves spectator mode
- Easy configuration via `config.yml`
- Lightweight and efficient

## Installation

1. Build the plugin using Maven: `mvn clean package`
2. Place the generated JAR in your server's `plugins` folder
3. Restart the server
4. Edit the `config.yml` in the `plugins/OwnerAFK/` folder
5. Set your username in the `owner-name` field
6. Restart the server again

## Configuration

Edit `plugins/OwnerAFK/config.yml`:

```yaml
# The username of the owner to monitor
owner-name: "YourName"

# The sidebar title when owner is AFK
sidebar:
  title: "&c&lOWNER STATUS"
  message: "&e&lOwner is AFK"
  info: "&7They will be back shortly!"

# Enable/Disable the plugin
enabled: true
```

### Color Codes

Use Minecraft color codes:
- `&c` = Red
- `&e` = Yellow
- `&7` = Gray
- `&l` = Bold
- `&m` = Strikethrough
- `&n` = Underline
- `&o` = Italic

See [Minecraft Wiki](https://minecraft.fandom.com/wiki/Formatting_codes) for more codes.

## How It Works

1. The plugin checks every second if the owner is online and in spectator mode
2. When the owner enters spectator mode, all online players see a sidebar
3. The sidebar is removed when the owner leaves spectator mode or logs off

## Building

```bash
mvn clean package
```

The compiled JAR will be in the `target/` folder.
