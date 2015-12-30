# NAND
NAND is a simple DSL I made as a proof of concept to myself.
The language only supports assignments and NAND operations.
If you find it interesting, you can just use the install script (this will add a shortcut to /usr/bin)

```
curl -s https://raw.githubusercontent.com/MichaelSnowden/nand/master/install.sh | sh
```

Or, you can build it from source (this won't add the shortcut).

```
git clone https://github.com/MichaelSnowden/nand
cd nand
mvn generate-sources
mvn clean compile assembly:single
java -jar target/nand.jar
```