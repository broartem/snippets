# VTK (The Visualization Toolkit) build scenarious
Prerequisites for Ubuntu (tested on version 15):
```
# Graphics drivers
sudo add-apt-repository ppa:oibaf/graphics-drivers
sudo apt-get update
sudo apt-get upgrade

sudo apt-get install cmake
sudo apt-get install libxt-dev

# see http://askubuntu.com/questions/575548
sudo apt-get install libglew-dev libcheese7 libcheese-gtk23 libclutter-gst-2.0-0 libcogl15 libclutter-gtk-1.0-0 libclutter-1.0-0
```

VTK with QT5 (see http://www.vtk.org/Wiki/VTK/Building/Linux):
```bash
cd /path/to/VTK-Release-build
cmake -DVTK_QT_VERSION:STRING=5 \
      -DQT_QMAKE_EXECUTABLE:PATH=/path/to/qt5.2.1-install/5.2.1/gcc_64/bin/qmake \
      -DVTK_Group_Qt:BOOL=ON \
      -DCMAKE_PREFIX_PATH:PATH=/path/to/qt.5.2.1-install/5.2.1/gcc_64/lib/cmake  \
      -DBUILD_SHARED_LIBS:BOOL=ON
      /path/to/VTK
```

VTK with Python support:
```bash
cmake -DVTK_WRAP_PYTHON:BOOL=ON /path/to/VTK
```
