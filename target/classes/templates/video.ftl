<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>VideoPlay</title>
</head>
<body>
  <video controls ="controls" style="width:720px;height:520px;" >
    <source src="/videoplay?videoname=${videoname}"  type="video/mp4" />
  </video>
</body>
<script type="text/javascript">
  //  controls="controls"  /*这个属性规定浏览器为该视频提供播放控件*/  
//         style="object-fit:fill"  /*加这个style会让 Android / web 的视频在微信里的视频全屏，如果是在手机上预览，会让视频的封面同视频一样大小*/
//         webkit-playsinline="true"  /*这个属性是ios 10中设置可以让视频在小窗内播放，也就是不是全屏播放*/  
//         x-webkit-airplay="true"  /*.支持Airplay的设备(如:音箱、Apple TV)播放*/
//         playsinline="true"  /*IOS微信浏览器支持小窗内播放*/
//         x5-video-player-type="h5" /*启用H5播放器,是wechat安卓版特性*/
//         x5-video-orientation="h5" /*播放器支付的方向，landscape横屏，portraint竖屏，默认值为竖屏*/
//         x5-video-player-fullscreen="true" /*全屏设置，设置为 true 是防止横屏*/
//         preload="auto" /*这个属性规定页面加载完成后载入视频*/
//         autoplay="autoplay" /**如果出现该属性，则视频在就绪后马上播放。**/
//         loop="loop"  /**如果出现该属性，则当媒介文件完成播放后再次开始播放。**/
//         poster="../assets/images/cms/v2.jpg"      /**规定视频正在下载时显示的图像，直到用户点击播放按钮。**/
//        muted="muted" /**如果出现该属性，视频的音频输出为静音。**/

</script>
</html>