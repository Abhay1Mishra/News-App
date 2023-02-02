package com.example.newsapp

object ColorPicker {
    var color:List<String> = arrayListOf("#00FFFF","#89CFF0","#0000FF","#7393B3","#088F8F","#0096FF","#5F9EA0","#0047AB","#6495ED")
   var colorIndex =1
    fun getColor():String{
        return color[colorIndex++ % color.size]
    }
}