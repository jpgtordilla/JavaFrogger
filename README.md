A clone of the classic arcade game, Frogger, made with the libGDX "cross-platform Java game development framework based on OpenGL (ES) that works on Windows, Linux, macOS, Android, your browser and iOS" (https://libgdx.com/). 

Sprites were created using the Texture library and depict a "realistic" frog, vehicles, and backdrop. File organization is based off of Object-Oriented Design principles and best-practices outlined by the libGDX documentation. All development was done in IntelliJ in Java. This current build is only supported on Desktop. 

https://github.com/jpgtordilla/JavaFrogger/assets/156870738/859fe8a5-f32c-45bf-82de-652fd82db5e6

A future revision would instead feature a "Vehicle" interface with "Boat" and "Car" classes that would implement its methods. I would also create a "VehicleHandler" class to take care of tasks such as spawning, movement, etc., using variables specific to the "Car" and "Boat" classes to handle variations in spawn location and frequency. However, due to the relative simplicity of this project, I chose to use a single Vehicle class and create Vehicle instances for "car" and "boat" within, similar to how an IntNode would build itself through instances of itself for an IntList.

