obd-java-api
============

OBD-II Java API

[![Build Status](https://drone.io/github.com/pires/obd-java-api/status.png)](https://drone.io/github.com/pires/obd-java-api/latest)

## Usage ##

### Maven ###
```
<dependency>
  <groupId>pt.lighthouselabs.obd</groupId>
  <artifactId>obd-java-api</artifactId>
  <version>0.5</version>
</dependency>
```

### Gradle ###
```
dependencies {
    compile 'pt.lighthouselabs.obd:obd-java-api:0.5'
}
```

### Example ###

After pairing and establishing Bluetooth connection to your ELM327 device..
```
...
// retrieve Bluetooth socket
socket = ...; // specific to the VM you're using (Java, Android, etc.)

// execute commands
new EchoOffObdCommand().run(socket.getInputStream(), socket.getOutputStream());
new LineFeedOffObdCommand().run(socket.getInputStream(), socket.getOutputStream());
new TimeoutObdCommand().run(socket.getInputStream(), socket.getOutputStream());
new SelectProtocolObdCommand(ObdProtocols.AUTO).run(socket.getInputStream(), socket.getOutputStream());
new AmbientAirTemperatureObdCommand().run(socket.getInputStream(), socket.getOutputStream());
...
```
