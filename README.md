obd-java-api
============

OBD-II Java API

## NOTICE

**I am no longer involved in any way with OBD and related activities, so don't expect my feedback on issues, pull-requests and most of all, email.**

I can't even remember when I first picked this project but it was fun as hell in the beginning. Now, years went by and a few contributors jumped in with amazing, smart features and fixes. To those fine people, **Thank you**! This is your _baby_, too.

Now, it's time to say goodbye.
Pires

[![Build status](https://circleci.com/gh/pires/obd-java-api.svg?style=svg)](https://circleci.com/gh/pires/obd-java-api)

## Important resources

Before opening an issue or using this library, please take a look at the following resources:

* [Understanding OBD](https://www.elmelectronics.com/help/obd/tips/#UnderstandingOBD)
* [The ELM327](https://www.elmelectronics.com/help/obd/tips/#327_Commands)

## Build ##

### Requisites ###

* JDK 7
* Maven 3.1 or newer

### Compile, package and install locally ###

```
mvn clean install
```

## Usage ##

### Maven ###
```
<dependency>
  <groupId>com.github.pires</groupId>
  <artifactId>obd-java-api</artifactId>
  <version>1.0</version>
</dependency>
```

### Gradle ###
```
dependencies {
    compile 'com.github.pires:obd-java-api:1.0'
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
  new EchoOffCommand().run(socket.getInputStream(), socket.getOutputStream());
  new LineFeedOffCommand().run(socket.getInputStream(), socket.getOutputStream());
  new TimeoutCommand(125).run(socket.getInputStream(), socket.getOutputStream());
  new SelectProtocolCommand(ObdProtocols.AUTO).run(socket.getInputStream(), socket.getOutputStream());
  new AmbientAirTemperatureCommand().run(socket.getInputStream(), socket.getOutputStream());
} catch (Exception e) {
  // handle errors
}
```

## Troubleshooting ##

As *@dembol* noted:

Have you checked your ELM327 adapter with Torque or Scanmaster to see if it works with your car? Maybe the problem is with your device?

Popular OBD diagnostic tools reset state and disable echo, spaces etc before protocol selection. Download some ELM327 terminal for android and try following commands in order:
```
ATD
ATZ
AT E0
AT L0
AT S0
AT H0
AT SP 0
```
