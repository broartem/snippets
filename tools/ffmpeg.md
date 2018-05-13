# FFMPEG snippets
Convert all MTS files to ProRes HQ:
```
for i in *.MTS; do name=`echo $i | cut -d'.' -f1`; echo $name; ffmpeg -i "$i" -vcodec prores -acodec pcm_s16le -ar 48000 -ac 2 -profile 3 "${name}.mov"; done
```
MPG to MP4:
```
ffmpeg -i input.MPG -f mp4 -vcodec libx264 -pix_fmt yuv420p output.mp4
```
