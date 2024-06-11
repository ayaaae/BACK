++++++++++++++++++++++ read carefuly++++++++++++++++++++++++++
1-project infos:
-maiven version:3.4.2
-jdk:19
2-usaly intelij ide build maven projects with jre but with jenkins we need to build with jdk thats why i have added u plugin in pom.xml for each micro-service to firce build with jdk and its looks like that :

++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                                                                                            

 <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.13.0</version>
                <configuration>
                    <verbose>true</verbose>
                    <fork>true</fork>
                    <executable>C:\Program Files\Java\jdk-19\bin\javac</executable>
                </configuration>
            </plugin>

+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

in the <executable> change with ur javac location in my case is [C:\Program Files\Java\jdk-19\bin\javac]</executable> but to bee syncronozed plz put it in the same location that i hv so:
1-install jdk-19
2-add bin to rnvirement variables
3-put the jdk folder in the same location [C:\Program Files\Java\]
            aaaaaaaaaaaa
