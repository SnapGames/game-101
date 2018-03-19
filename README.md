# [PROJECT_NAME]

[![AppVeyor (win32) Build status](https://ci.appveyor.com/api/projects/status/github/[REPO_OWNER]/[REPO_NAME]?branch=[REPO_BRANCH:develop]&svg=true)](https://ci.appveyor.com/project/[REPO_OWNER]/[REPO_NAME] "AppVeyor (win32) Build status") 
[![Travis-CI (linux) Build Status](https://travis-ci.org/SnapGames/simple-java2d-engine.svg)](https://travis-ci.org/[REPO_OWNER]/[REPO_NAME] "Travis-CI (linux) Build Status") 
[![Dependency Status](https://www.versioneye.com/user/projects/[VERSIONEYE_PROJECT_ID]/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/[VERSIONEYE_PROJECT_ID] "Dependency statues")

## Introduction

TODO

## Project structure

The maven project is mostly standard:

    [PROJECT_NAME]/
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

Bellow the satandard pom.xml file, the Continuous integration files are :

* appveyor.yml
* bitbucket.org


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
    $> java -jar [PROJECT_NAME]-[PROJECT_VERSION]-jar-with-dependencies.jar
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
* ...



[[AUTHOR_NAME]](mailto:[AUTHOR_MAIL] "send a mail to contact")
