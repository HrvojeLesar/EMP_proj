# Struktura projekta
- `src/` sadrži sav izvorni kod programa
- `demoPodatci/` sadrže podatke koji se koriste u programu (XML i XSD datoteke koje se parsiraju)

# Kompaliranje (pretpostavlja se korištenje komandne linije)
1. Za kompaliranje projekta potrebno je imati instaliran [Apache Maven](https://maven.apache.org/).
2. Potrebno je pokrenuti komandu `mvn install` kako bi se paketi na kojima zavisi projekt (JAXB)
3. Za kompilaciju i pokretanje može se koristiti komanda `mvn compile exec:java -Dexec.mainClass="org.foi.emp.hlesar.App" -q`
