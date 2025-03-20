# PredictIF

PREDICT'IF is an application that allows clients to schedule appointments with mediums embodied by company employees. This application can be used by two types of users: employees and clients.

## Features

### From the client's perspective

- **Account Creation**: Clients can create an account by providing:
  - First name
  - Last name
  - Email (used as the identifier)
  - Phone number
  - Date of birth (format: yyyy-mm-dd)
  - Password
  - Gender (only 'M' for male and 'F' for female)
  - Postal address
  - **Condition**: No other account should exist with the same email address.

- **Authentication**: 
  - Prerequisite: The client must have already created an account.
  - Credentials: Email and password.

- **Access to Features**:
  - **Astrological Profile**: Displays the zodiac sign, totem animal, lucky color, and Chinese zodiac sign.
  - **Consultation History**: View past appointments.
  - **Request a New Consultation**:
    - Displays a list of mediums with their information (bio, gender, medium type, training, promotion).
    - Enter the ID of the chosen medium to confirm the appointment.
    - **Conditions**:
      - The choice of employee depends on the requested medium's gender and the number of consultations already performed.
      - If no employee is available to embody the medium, the request is rejected.
  - **Logout**: The client can exit the application at any time.

### From the employee's perspective

- **Authentication**: 
  - Employees are already registered in the database.
  - Credentials: Email and password (same method as for clients).

- **Access to Features**:
  - **Client Consultation**: View all clients in the database and access their information (astrological profile, history) by entering their ID.
  - **Statistics**:
    - TOP 5 mediums generating the most consultations.
    - Consultation distribution by employee.
    - Consultation distribution by medium.
  - **Start Consultations**:
    - Conditions: The consultation must be assigned to them (chosen by a client) and in progress (no comments have been added yet).
    - **Automatic Predictions**: Generate predictions for the client in the areas of love, health, and work (with a score from 1 to 4).
    - **End Consultation**: Add a comment.
    - **History**: View the history of completed consultations, including the current consultation.
  - **Logout**: The employee can exit the application at any time.
