# CS 352: Internet Technology- Midterm 07/19

##Exam Content  
**The exam has been partitioned into 6 sections totaling 114 pts**
0. <span style = "color:red">Internetworking ~ 24 pts</span>
1. <span style = "color:red">TCP Details ~ 20 pts</span>
2. <span style = "color:red">Delay and Transport ~ 20 pts</span>
3. <span style = "color:red">Transport Calc ~ 20 pts</span>
4. <span style = "color:red">HTTP Protocol ~ 30 pts</span>
5. <span style = "color:red">Extra ~ 3 pts</span>

## Terms
* **Network**: Collection of interconnected *machines*
* **Internetwork**: Collection of interconnected *networks*
* **Host**: Machine running user application
* **Channel**: *Logical* line of communication
* **Medium**: Physical process used (e.g. fiber optics, copper wire, satellite link)
* **Network Core**: The mesh of packet switches and links that interconnect the Internet's end systems
* **EDGE Network**:
* **CORE Network**: ISP tiers with the biggest ISPs in tier 1 and smaller, regional ISPs in tiers 2, 3
* **Protocol**: Defines the format and the order of messages exchanged between two or more communicating entities, as well as he actions taken on he transmission and/or receipt of a message or other event
* **Packets**: Smaller chunks of data broken off from longer "messages"
* **Packet Switching**: Group data transmitted over the network into packets which then get transmitted (e.g. routers, link-layer switches)
* **Store-And-Forward**: Packet switch must receive the entire packet to transmit the first bit of packet onto outbound link
* **Output Buffer**: An *output queue* that stores the packets that the router is about to send into a link
* **Queueing Delays**: Variable delays that depend on the level of congestion in the network and occurs when an incoming packet finds the link busy with transmission of another packet
* **Processing Delay**: The time required to examine the packet's header and determine where to direct the packet
* **Transmission Delay**: Amount of time required to push/transmit  all of the packet's bits into the link
* **Propagation Delay**: The time required to propagate from the beginning to router B
* **Packet Loss**: The arriving packet or an already-queued packet may be dropped if an arriving packet finds that the buffer is completely full with other packets waiting for transmission
* **Forwarding Table**: Each router has one of these that maps destination address (or portions of it) to that router's outbound links
* **Circuit Switching**: A circuit-based network that guarantees a constant transmission rate between a sender and receiver. For these purposes, a dedicated *end-to-end* connection exists
* **Frequency-Division Multiplexing**: The frequency spectrum of a link is divided up among the connections across a link; the link dedicates a frequency band to each connection for the duration of the connection.
* **Time-Division Multiplexing**: Time is divided into frames of fixed duration, and each frame is divided into a fixed number of time slots. When network establishes a connection across a link, the network dedicates one time slot for the sole use of that connection in every frame to this connection.
*  **Statistical Multiplexing**:
* **Encapsulation**: The process of passing information through the OSI stack, usually by appending header and payload.
* **Client-Server Architecture**: An always-on *host* accepts service requests from incoming *clients*, but the clients do not directly communicate with each other
* **P2P Architecture**: The application exploits direct communication between intermittently connected hosts, or *peers*, without passing through a dedicated server
* **Socket**: The interface between your application and the network; one of the endpoints in a two-way communication link between two programs running on the network

## ISO-OSI Seven-Layer Stack
![stack](/notes/img/osi_summary.gif)

![encaps-decaps](/notes/img/encap_decap_osi.jpg)

## Client-Server vs. P2P
![architectures](/notes/img/architecture_comparison.PNG)

## HTTP 1.0
###Response Codes  

Status Code| Response| Meaning |
:--:|:---:|:--:|
200  | OK | 						 &nbsp;Success
304  | Not Modified | Redirection
400  | Bad Request | Client error
403  | Forbidden | Client error
404  | Not Found | Client error
408  | Request Timeout | Client error
418  | I'm a Teapot | For Fun
500  | Internal Server Error | Server error
501  | Not Implemented | Server error
503  | Service Unavailable | Server error
505  | HTTP Version Not Supported | Server error  


###MIME Types  
**HTTP 1.0 also supports multi-parts and messages**
Extension| Kind of Document| MIME Type |
:--:            |:---:|:--:|
.txt             | default text file | text/plain
. htm, . html   | HTML | text/html
. *             | default for all other file types | application/octet-stream
. gif           | GIF | image/gif
. jpg           | JPEG Images |image/jpeg
. *             | Basic Audio Files | audio/basic
.mpeg           | MPEG Video | video/mpeg


##Delay and Transport (with Calculations)
* Processing, Queueing, Transmission, and Propagation Delays are as defined in the definitions section
* Network: *d*<sub>nodal</sub> = *d*<sub>proc</sub> + *d*<sub>queue</sub> + *d*<sub>trans</sub> + *d*<sub>prop</sub>
* Transport  
	![nodes](/notes/img/switching_node.gif)
	* Circuit Switching: **t = C + M/B**  
		* C: connection setup time (secs)  
		* M: message size (bits)  = packet data size + header size
		* B: bandwidth (bits/sec)  
		![circuit](/notes/img/circuit_switch.gif)
	* Packet Switching: **t = t<sub>0</sub> * (P + S)**  
		* h: header size  
		* d: packet data size (payload)  
		* t0: time it takes to send one packet = (h+d)/B  
		* B: bandwidth (bits/sec)
		* S: no. of stations (hops) going through
		* P: number of packets to send = ⌈M/d⌉  
		![circuit](/notes/img/packet_switch.gif)
	* Message Switching: **t = t<sub>0</sub> * (1 + S)**
		* h: header size
		* t0: time it takes to send one message = (h+M)/B
		* M: message size (bits)
		* B: bandwidth (bits/sec)
		* S: no. of stations (hops) going through  
		![circuit](/notes/img/message_switch.gif)

##Useful Comparisons
###TCP vs. UDP
|       PRO/CON    | TCP  | UDP |
:-:                | :--: |:--: |
connectionless     |   -  |  X  |
faster             |   -  | X   |
more reliable      |	 X  |  -  |
provides ports     |	-		|  X  |
more functionality |	X	  |  -  |
###HTTP vs. FTP vs. SMTP
|           FEATURE       | HTTP           | FTP | SMTP |
:-:                       |:--:            |:---:|:--:  |
request/response protocol | X              | X   | X
connection semantics      | -              | X   | X    |
###Circuit vs. Packet vs. Message Switching
* **Header Overhead**: Circuit < Packet < Message
* **Transmission Delay**:
	* **_Short, Bursty Messages_**: Packet < Message < Circuit
	* **_Long, Continuous Messages_**: Circuit < Message < Packet
* Circuit vs. Packet
	* Still (TDM, FDM) vs. Statistical Multiplexing
	* Less vs. More Users
