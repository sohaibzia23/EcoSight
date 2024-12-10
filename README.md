# Welcome to EcoSight backend

This is the backend code for EcoSight. You’ll need mysql and an azure account to make this work. The azure account won’t cost you though.

---

### Setup

1. Clone the repo

```bash
git clone https://github.com/sohaibzia23/EcoSight.git
```

1. Setup your environmental variables for the project

AZURE_CONTAINER_NAME

AZURE_STORAGE_CONNECTION

DATABASE_PASSWORD

DATABASE_URL

DATABASE_USERNAME

---

### Getting azure environmental variables (free)

- Create an Azure Account:
    - Go to portal.azure.com
    - Click "Start Free" or "Create a free account"
    - You'll need:
        - Email address
        - Credit/debit card (for verification, won't be charged for free tier)
        - Phone number
- Create a Storage Account:
    - Once logged in, click "Create a resource"
    - Search for "Storage account"
    - Click "Create" under Storage Account
    - Fill in:
        - Subscription: Your subscription (likely "Free Trial")
        - Resource Group: Create new, give it a name like "ecosight-resources"
        - Storage account name: Choose a unique name (like "ecosightstorage")
        - Region: Choose one close to you
        - Leave other settings as default
    - Click "Review + Create", then "Create"
    - Wait for deployment to complete
- Get the Connection String:
    - Go to your new storage account
    - In left sidebar, under "Security + networking", click "Access keys"
    - Find "Connection string"
    - Click "Show" then copy the entire string
    - This is your AZURE_STORAGE_CONNECTION value
- Create and Get Container Name:
    - In the left sidebar, click "Containers"
    - Click "+ Container"
    - Name it (e.g., "ecosight-images") - this will be your AZURE_CONTAINER_NAME
    - Set "Public access level" to "Blob" (allows public read access)
    - Click "Create"

# ENJOY!
