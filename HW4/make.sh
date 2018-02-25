
# ASSIGNMENT:    Assignment 4
# AUTHOR:        Srizan Gangol
# DESCRIPTION:  Compiles all programs and Runs

echo -e "\n[Starting Compilation]"
echo -e   "----------------------"

jflex MicroScala.jflex
echo -e "\n$ jflex MicroScala.jflex [..OK] \n"

echo -n "$ javac MicroScalaLexer.java [.."
javac MicroScalaLexer.java AbstractSyntaxTree.java
echo -e "OK]"

echo -n "$ javac HW4.java [.."
javac HW4.java
echo -e "OK]\n"

echo -e "> COMPILATION COMPLETED!!"
echo -e "-----------------------\n"

echo -e "CHECKING TEST CASE #1 \n"
echo -e "--------------------- \n"
echo -e "$ java HW4 < Test1.scala  \n"
java HW4 < Test1.scala
echo -e "\nComiplation and Run with Test1.scala completed\n"

echo -e "Follow following command for other Test cases"
echo -e "$ java HW4 < Test-.scala \n"
