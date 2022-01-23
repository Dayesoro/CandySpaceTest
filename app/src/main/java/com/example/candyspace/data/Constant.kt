package com.example.candyspace.data

object Constant {
    const val BASE_URL="https://api.stackexchange.com/2.3/"

    //Api filters const
    const val ORDER="asc"
    const val PAGE="1"
    const val PAGE_SIZE="20"
    const val SORT="reputation"
    const val SITE="stackoverflow"
}

class Car constructor(val wheel: Wheel){

fun statrt(){
wheel.move()
}

}

class Wheel(){
   fun move(){

   }
}

class main(){
    val wheel=Wheel()
}

class Gear(){

}