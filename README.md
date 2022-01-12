## Xml To Database

#### Project description:

The task of the project is to transfer a large (2-3 GB) .xml file with objects of the same type to the database.

Objects in the file can be duplicated, after the transfer there should not be duplicates in the database.

DB - postgreSQL. \
Example .xml file:
```
<orgs>
    <org>
        <name>Организация №1</name>
        <inn>123456789</inn>
        <ogrn>123456789012</ogrn>
        <address>г.Москва ул.Терехина д.6 оф.515</address>
        <postcode>123456</postcode>
        <openDate>20.12.2014</openDate>
    </org>
    <org>
        ...
    </org>
    ...
</orgs>
```

---

#### Procedure:
In the Main class in the root folder.
1. Build .xml file
2. Parse and send to database. (With batch in PrepareStatement, not threads)
3. Enter result

---

#### Class and methods description:
Classes:
- **Org** - *Object in file.xml  `<org>...</org>`*
- **RandomData** - *generating data to build a file.xml*
- **XMLBuilder** - *building the file.xml structure*
- **XMLParser** - *parsing the file.xml*
- **DirectoryDao** - *connect and send request in database*