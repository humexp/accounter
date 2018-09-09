# Accounter

- - - -

## Usage
### Run tests
```
    gradlew test
```

### Start application
```
    gradlew clean shadowJar
    java -jar build/libs/accounter.jar
```

## Api
### Create Account
PUT - `api/account`
```
{
    "id" : 20100123123,
    "company" : "Bellagio",
    "iban" : "CY21002001950000357001234567",
    "balance" : "15000.00",
    "activityStatus" : "active"
}
```

### Get Account
GET - `api/account`

### Get all Accounts
GET - `api/account/all`

### Execute Transfer
POST - `api/transfer`
```
{
  "accountFrom": 20100123124,
  "accountTo": 20100123125,
  "sum": "1000.22"
}
```
