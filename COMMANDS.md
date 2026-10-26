## Java

- `ps -ef | grep java` - To list all java process

### JVM options

- `-Xms512M` - Minimum memory needed for Heap
- `-Xmx512M` - Maximum memory needed for Heap
- `-XX:NewRatio=2` - Sets the ratio between Young and Old Generation. A ratio of 2 means Young:Old is 1:2 (Young Gen is 33% of total heap).
- `-XX:SurvivorRatio=8` - Sets the ratio between Eden and a single Survivor space (e.g., 8 means Eden is 8x the size of S0/S1).
- `-XX:MaxTenuringThreshold=15` - Sets the maximum age before an object is promoted to the Old Generation.