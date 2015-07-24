# Apollo Mqtt Util

Packaging some util for Apache Apollo.

## Usage
1. Build your apollo service. :P
2. Build this util with gradle:

```
$ cd <project_root_directory>
$ gradle jar
```
and you'll find the jar in <project_root_directory>/build/libs.
3. Set config params in ConfigBO to init apollo service.
4. Set the destination and content in TopicBO.
5. Call publish function with TopicBO and ConfigBO params in Apollo interface to send message.
6. You could also get message with subscribe function.

## Version History:

- 0.0.1-SNAPSHOT  
  - Initial version: Publisher has been completed.
    
- 0.0.2-SNAPSHOT
  - Subscribe use callback api has been completed.
  
- 0.0.3-SNAPSHOT
  - Fixed some exception handler's bugs.
  
- 0.3.1-SNAPSHOT
  - Modify build.gradle.