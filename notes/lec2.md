# Lecture 2, 06/28/17

## LAST UPDATED: 06/28/17 06:24:48

## Host
 * anything that hooks up to the internet
 * endpoint in a network
 * 'edge' of a network
 * identified by an IP address
 * a machine connected to a network that runs applications

## Edge
  * contains hosts running applications (user by users)

## Core
  * Elements that support/relay/switch traffic for the Edge (no app, no users)

## ISO OSI Stack
|Layer No. | Name|
|:--------:|:---:|
7  |  Application  
6  |  Presentation
5  |  Session
4  |  Transport
3  |  Network
2  |  Data Link
1  |  Physical

**As you move up the stack, there's more abstraction, but as you move down, more bits are being stored due to ENCAPSULATION**

## Workable but less abbreviated stack
|Layer No. | Name|  
|:--------:|:---:|
4  |  Application  
3  |  Transport
2  |  Network
1  |  Host-to-PHY (DL/PHY)

## TCP vs. UDP - Transport (Message Transmission)
  * TCP provides many functionality
  * UDP provides ports (not so exciting)
  * TCP is more reliable but UDP is faster (tradeoffs)
  * UDP is connectionless as opposed to TCP
  * SSH (bash, cmd, etc.) is part of the Application layer and must be built using TCP
  * Keep the following in mind:
	  1. Reliability
	  2. Header Size
	  3. Lost/Unsuccessful Communication
	  4. Flow Control
	  5. Congestion Control

## Exponential Math
    | Unit | Value     |
    | :--: | :-------: |
    | KB   | 2^10|
    | MB   | 2^20 = (2^10)^2|
    | GB   | 2^30|
    | TB   | 2^40|
    | PB   | 2^50|

  **Note: 2^5 = 32 && 2^10 = 1024**

  * 1 KB/ks = (2^10) B / (10^3) sec
  * Example
    * 256 MB = 2^8 * 2^20 = 2^28 (download)
    * 4 MB/s = 2^2 * 2^20 = 2^22 (download rate)
    * 2^8/2^2 = 64 s time

## Threads
  * Used to support concurrent execution within an application. So basically accepts multiple connections at the same time.
  * Scheduling ~ OS Schedulers
  * Concurrency needed cuz maybe the other operations like disk access, blocking call, etc. need to be done.
  * Average seek-time on disk is 80-90 s but on internet is 40 s
  * Needs to support many connections at once
  * Allows each client independent services

## Java Threads
  * Create class that extends Thread OR implements Runnable
    * must implement run method
  * Instantiate this class OR a Thread to run this Runnable
  * Invoking run method starts new execution path
    * After caller returns, the run method (and original exec) is still running!
    * Calling join method waits for the run method to terminate
