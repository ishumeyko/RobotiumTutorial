RobotiumTutorial
================

A simple Android Application with a suite of tests in Robotium.

The tests contain my notes on the different ways the platform can be used.  It's a work in progress.

The tictacro package contains an interactive game, play against the Robot! Don't let him infect your Activity! This is also a work in progress. For now all the Robot can do is (randomly) select moves.

Deploy RobotiumTutorial as a standalone Application or run a TestRobotiumTutorial source file as an Android Test.

To run the test that involves Fragments, testPagerFragmentsPickersRadioActivity():
Add the support-v4 lib to TestRobotiumTutorial's classpath, with a scope of "Provided"



    To setup on Intellij for Mac or Windows:

        Import a project from existing sources from the root RobotiumTutorial folder.

        Choose both the RobotiumTutorial and TestRobotiumTutorial src folders as source folders.

        Create an Android project, API level 15 (4.0.3) recommended.

