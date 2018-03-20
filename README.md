# Game-101

[![AppVeyor (win32) Build status](https://ci.appveyor.com/api/projects/status/github/SnapGames/game-101?branch=develop&svg=true)](https://ci.appveyor.com/project/SnapGames/game-101 "AppVeyor (win32) Build status") 
[![Travis-CI (linux) Build Status](https://travis-ci.org/SnapGames/game-101.svg)](https://travis-ci.org/SnapGames/game-101 "Travis-CI (linux) Build Status") 
[![Dependency Status](https://www.versioneye.com/user/projects/[VERSIONEYE_PROJECT_ID]/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/[VERSIONEYE_PROJECT_ID] "Dependency statues")

## Introduction

TODO

## Project structure

The maven project is mostly standard:

    Game-101/
     |_ src
     |  |_ main 
     |  |  |_ java 
     |  |  |_ resources 
     |  |_ test 
     |  |  |_ java 
     |  |  |_ resources 
     |_ .gitignore
     |_ .travis.yml
     |_ appveyor.yml
     |_ bitbucket-pipelines.yml
     |_ pom.xml
     |_ README.md

Bellow the standard pom.xml and git file, the Continuous integration files are :

* `appveyor.yml` to provide configuration to [Appveyor.com](https://appveyor.com/ "go and visit AppVeyor") continuous integration service,
* `bitbucket-pipeline.yml`  to provide configuration to [Bitbucket.org](https://bitbucket.org "go and visit BitBucket") continuous integration service,
* `.travis.yml`  to provide configuraiton to [Travis-CI](https://travis-ci.org "go and visit Travis-CI") continuous integration service.



## Compile

To compile the full project, please execute the following command :

```bash
    $> mvn clean install
```

## Execute the program

- **On any platform (Linux, MacOS, Windows)**

to execute the compiled jar, please execute the command bellow :

```bash
    $> mvn exec:java
```

or :

```bash
    $> java -jar Game-101-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

## Publish as a Maven Repo

To publish to the right maven repo, just execute the following lines:

```bash
    $> mvn clean site deploy
```

Before execution, be sure that your `settings.xml` contains a `server` entry with your login/password for the github repository.

	<servers>
		<server>
			<id>github</id>
			<username>[GITHUB-USERNAME]</username>
			<password>[GITHUB-USERPASSWORD]</password>
		</server>
	</servers>



## References

* [https://www.google.com/](https://www.google.com/ "open the reference")



[Frédéric Delorme](mailto:frederic.delorme@snapgames.fr "send a mail to contact")
