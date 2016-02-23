#!/bin/bash
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
export PATH="$PATH:/home/igladush/IdeaProjects/FirstProject/JavaFirst/lib"



