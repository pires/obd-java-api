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
  <version>0.9</version>
</dependency>
```

### Gradle ###
```
dependencies {
    compile 'pt.lighthouselabs.obd:obd-java-api:0.9'
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

## Troubleshooting ##

As *@dembol* noted:

Have you checked your ELM327 adapter with Torque or Scanmaster to see if it works with your car? Maybe the problem is with your device?

Popular OBD diagnostic tools reset state and disable echo, spaces etc before protocol selection. Download some elm327 terminal for android and try following commands in order:
```
ATD
ATZ
AT E0
AT L0
AT S0
AT H0
AT SP 0
```
