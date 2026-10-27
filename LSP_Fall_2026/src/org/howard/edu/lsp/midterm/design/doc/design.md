# Proposed CRC Cards

## Class: RegistrationService

**Responsibilities:**
- Coordinate the student registration process.
- Request tuition calculation.
- Save registration records.
- Request that a confirmation be sent.
- Ensure the main registration steps occur in the correct order.

**Collaborators:**
- TuitionCalculator
- RegistrationRepository
- ConfirmationService
- Registration

## Class: Registration

**Responsibilities:**
- Store registration information, including student ID, student name, email, course code, and credit hours.
- Provide access to registration information.
- Represent one student's registration for a course.

**Collaborators:**
- RegistrationService

## Class: TuitionCalculator

**Responsibilities:**
- Calculate tuition based on credit hours.
- Apply the appropriate discount when a student takes at least 12 credit hours.
- Return the calculated tuition amount.

**Collaborators:**
- Registration

## Class: RegistrationRepository

**Responsibilities:**
- Define the operations needed to save and retrieve registration records.
- Provide a common interface for registration storage.
- Keep storage details separate from registration processing.

**Collaborators:**
- RegistrationService
- FileRegistrationRepository
- DatabaseRegistrationRepository
- Registration

## Class: FileRegistrationRepository

**Responsibilities:**
- Save registration records to a local file.
- Handle file-specific storage operations.

**Collaborators:**
- RegistrationRepository
- Registration

## Class: DatabaseRegistrationRepository

**Responsibilities:**
- Save registration records to a database.
- Handle database-specific storage operations.

**Collaborators:**
- RegistrationRepository
- Registration

## Class: ConfirmationService

**Responsibilities:**
- Define the operation for sending registration confirmations.
- Coordinate the delivery of a confirmation using the selected communication method.

**Collaborators:**
- RegistrationService
- EmailConfirmation
- TextConfirmation
- Registration

## Class: EmailConfirmation

**Responsibilities:**
- Send a registration confirmation through email.
- Use the student's email address as the destination.

**Collaborators:**
- ConfirmationService
- Registration

## Class: TextConfirmation

**Responsibilities:**
- Send a registration confirmation through text message.
- Use the student's phone number as the destination.

**Collaborators:**
- ConfirmationService
- Registration