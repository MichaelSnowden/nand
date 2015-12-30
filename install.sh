#!/usr/bin/env bash
git clone https://github.com/MichaelSnowden/nand
cd nand
mvn generate-sources
mvn clean compile assembly:single
echo "#!/usr/bin/env bash" >> nand.sh
echo "java -jar $(pwd)/target/nand.jar \$1" >> nand.sh
sudo chmod 777 nand.sh
sudo cp nand.sh /usr/bin/nand
echo "Installation complete! We will now open the REPL"
nand