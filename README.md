obd-java-api
============

OBD-II Java API

[![Build Status](https://drone.io/github.com/pires/obd-java-api/status.png)](https://drone.io/github.com/pires/obd-java-api/latest)

## Build ##

### Requisites ###

* JDK 7
* Maven 3.1.0 or newer

### Compile, package and install locally ###

```
mvn clean install
```

## Usage ##

### Maven ###
```
<dependency>
  <groupId>pt.lighthouselabs.obd</groupId>
  <artifactId>obd-java-api</artifactId>
  <version>0.6</version>
</dependency>
```

### Gradle ###
```
dependencies {
    compile 'pt.lighthouselabs.obd:obd-java-api:0.6'
}
```

### Example ###

After pairing and establishing Bluetooth connection to your ELM327 device..
```
...
// retrieve Bluetooth socket
socket = ...; // specific to the VM you're using (Java, Android, etc.)

// execute commands
try {
  new EchoOffObdCommand().run(socket.getInputStream(), socket.getOutputStream());
  new LineFeedOffObdCommand().run(socket.getInputStream(), socket.getOutputStream());
  new TimeoutObdCommand().run(socket.getInputStream(), socket.getOutputStream());
  new SelectProtocolObdCommand(ObdProtocols.AUTO).run(socket.getInputStream(), socket.getOutputStream());
  new AmbientAirTemperatureObdCommand().run(socket.getInputStream(), socket.getOutputStream());
} catch (Exception e) {
  // handle errors
}
```
