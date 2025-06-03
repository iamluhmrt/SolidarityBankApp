# SolidarityBankApp

SolidarityBankApp is a fully functional Android application designed to manage donations efficiently and intuitively. Originally developed as "Solidarity Bank," this app provides a seamless experience for registering, viewing, and deleting donation records.

## Features

- **Add Donations:** Easily register new donations with donor name, item name, description, and quantity.
- **View Donations:** Browse all registered donations in a clean and responsive list using RecyclerView.
- **Delete Donations:** Remove donations directly from the list with confirmation dialogs to prevent accidental deletion.
- **Local Persistence:** All data is stored locally on the device using SQLite for offline access and reliability.
- **Clean UI:** Uses Material Design components and CardView to provide a modern, user-friendly interface.
- **Efficient Codebase:** Modular architecture separating concerns, following Android best practices.
- **Responsive Navigation:** Intuitive navigation flow with dedicated activities and consistent use of buttons.

## Getting Started

### Prerequisites

- Android Studio Arctic Fox or later
- Android SDK API level 24 (Nougat) or higher
- Gradle 7.0 or later

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/<your-username>/SolidarityBankApp.git
2. Open the project in Android Studio.
3. Let Android Studio sync and build the project dependencies.
4. Run the app on a physical device or emulator.

Project Structure
com.pucpr.bancosolidario - Main package containing all activities, adapters, and database helper.

DBHelper - SQLite helper class managing database operations.

DoacaoAdapter - RecyclerView adapter for donation list items.

Activities:

MainActivity - Entry point with navigation to register or view donations.

CadastroActivity - Donation registration form.

ListaDoacoesActivity - Donation list and management.

## Project Structure

- **com.pucpr.bancosolidario** - Main package containing all activities, adapters, and database helper.

- **DBHelper** - SQLite helper class managing database operations.

- **DoacaoAdapter** - RecyclerView adapter for donation list items.

- **Activities:**
  - **MainActivity** - Entry point with navigation to register or view donations.
  - **CadastroActivity** - Donation registration form.
  - **ListaDoacoesActivity** - Donation list and management.



## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
