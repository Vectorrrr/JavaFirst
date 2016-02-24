#Add Path to dir lib
PROJECT_DIR=/home/igladush/IdeaProjects/FirstProject/JavaFirst
LIB=$PROJECT_DIR"/lib"
LISTOFFILE=$PROJECT_DIR"/listLib.txt"
echo 10
#Check exist file
if [ ! -e $LIB ]
then 
   echo 13
   /bin/mkdir -p $LIB
   echo 11
fi
while IFS= read -r line
  do
   T=$LIB"/"$line
   if [ ! -e $T ]
     then 
     TEMP="https://github.com/Vectorrrr/Test/blob/master/"$line
     echo "Download...."$line
     #downloadfile
     wget -P $LIB $TEMP
   fi
done <"$LISTOFFILE" 
PATH_TO_COMPILE=$PROJECT_DIR"/out"
if [ ! -e $PATH_TO_COMPILE ]
then
   /bin/mkdir -p $PATH_TO_COMPILE
fi
echo "Compile..... reader"
javac -d ./out ./src/view/reader/*
echo "Compile..... writer"
javac -d ./out ./src/view/writer/*
echo "Compile..... function"
javac -d ./out ./src/model/function/*
echo "Compile..... operation"
javac -d ./out ./src/model/operation/*
echo "Compile..... ManagerSettings"
javac -d ./out -cp ./out ./src/model/ManagerSettings.java 
echo "Compile..... TextSearchResult"
javac -d ./out ./src/model/TextSearchResult.java
echo "Compile..... View"
javac -d ./out -cp ./out ./src/view/View.java
echo "Compile..... Service"
javac -d ./out -cp ./out ./src/logic/service/*
echo "Compile..... Controller"
javac -d ./out -cp ./out ./src/logic/Controller.java
echo "Compile..... Main"
javac -d ./out -cp ./out ./src/Main.java
echo "Run.... Main"
java -cp ./lib -classpath ./out Main



