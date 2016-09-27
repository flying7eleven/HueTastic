# HueTastic
HueTastic is a small hobby project to operate Hue bridges / devices in your local network. It started mid-2016 due to the lack of apps with a reasonable good UI. The goal of this project is to have a good integration into the operating system and a good usability.

## How to build
To build the app yourself, you have to make sure that you have developer access to the [Philips Hue Developer Program][1]. There you can download the Java/Android SDK and place the corresponding jar-files in the ```3rdparty``` folder of the source tree. After doing that, you can build the app using ```gradle```.

## Techical

### API
To operate the Hue bridges, the official [API][1] is used.

## Progress
The first available version of the app should very rudimental and just provide functions for finding a Hue bridge as well as managing the already added lightbulbs. From this stage, all functions which are required for a daily use are added in small increments.

### Ideas
One idea would be to handle light colors like in [Huetro][4]. You can upload an image (which also is the scene image) and place color pickers onto the image. These color pickers also represent the single lights and their color.

### First version
You can find the planed features for the first version of the app in the [project plan][3].

## Source references
The app was developed using 3rd-party assets. This section describes which artist developed which asset and where it was used.

| Resource type | Artist        | Description                                            | Source      |
|---------------|---------------|--------------------------------------------------------|-------------|
| Video         | Sean Hobson   | Video in the screen where the bridges are discovered   | [Flickr][5] |
| Picture       | Tim Parkinson | Picture in the screen where the bridges are discovered | [Flickr][6] |

[1]: http://www.developers.meethue.com/
[2]: https://www.huetz.biz/apps/huetastic
[3]: https://github.com/thuetz/HueTastic/projects/1
[4]: https://www.microsoft.com/en-us/store/p/huetro-for-hue/9wzdncrfjj3t
[5]: https://www.flickr.com/photos/seanhobson/4083815314
[6]: https://www.flickr.com/photos/timparkinson/3125954608
