# DB Performance for Java developers

## Introduction
This small repo was created for a DB Performance presentation I gave for colleagues. The focus of the presentation was Normalization, Indexes and a comparison between data business logic in native code vs Java code. This repository is the demo I used for that last part.

## Structure
This is a simple Spring Boot application that connects to a dockerized PostgreSQL instance and runs a couple of tests after Spring startup.

## Contents
This demo code is not optimized in any way, neither the DB code or the Java code. A simple piece of business logic is performed 3 times, once as native DB code (PostgreSQL plpgsql procedure), once as Java code and once as a single SQL statement.

## Extra
The code contains a couple if things that might be interesting besides the performance test part:
* Running code after application startup in Spring Boot (see the EventListener in DbPerformanceApplication.java)
* How to call a plpgsql procedure from Java (see CustomerRepository.java)
* How to pass an array of values to that procedure (see string_to_array in calculate_discount procedure in snippets.sql)
* How to correctly measured elapsed time in Java using System.nanoTime() (see the benchmarkservices)
* How to seed the database with testdata using the Java Faker library [1] (see Initializer.java)

[1] https://github.com/DiUS/java-faker