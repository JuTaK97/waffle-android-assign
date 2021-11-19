package com.example.assignment1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Math.abs


class MainViewModel : ViewModel() {


    private val _data = MutableLiveData(Array(9){_->' '})   // data of each button
    val data : LiveData<Array<Char>> = _data

    private val _status = MutableLiveData("Playing...")    // game's current status
    val status : LiveData<String> = _status

    private var ox = true           // whose turn?
    private var gameover = false    // to stop everything when game ends

    fun click(i : Int) {
        if(gameover) return

        if (_data.value?.get(i)==' ') {
            if (ox)    _data.value?.set(i, 'O')
            else       _data.value?.set(i, 'X')
            ox = !ox
            _data.value = _data.value
            check()
        }
    }

    private fun check() {
        if(gameover) return

        val num_rep = IntArray(9)
        for(i in 0 until 9) {
            if(_data.value?.get(i)==' ') num_rep[i]=0
            else if(_data.value?.get(i)=='O') num_rep[i]=1
            else num_rep[i]=-1
        }

        if(abs(num_rep[0]+num_rep[1]+num_rep[2])==3 ||
           abs(num_rep[3]+num_rep[4]+num_rep[5])==3 ||
           abs(num_rep[6]+num_rep[7]+num_rep[8])==3 ||

           abs(num_rep[0]+num_rep[3]+num_rep[6])==3 ||
           abs(num_rep[1]+num_rep[4]+num_rep[7])==3 ||
           abs(num_rep[2]+num_rep[5]+num_rep[8])==3 ||

           abs(num_rep[0]+num_rep[4]+num_rep[8])==3 ||
           abs(num_rep[2]+num_rep[4]+num_rep[6])==3){
            if(ox) _status.value = "Player X wins!"
            else _status.value = "Player O wins!"
            gameover = true
            return
        }

        var draw = true
        for(i in 0 until 9) {
            if(_data.value?.get(i)==' ') {
                draw = false
                break
            }
        }
        if(draw) {
            _status.value = "Draw!"
            gameover = true
        }
    }
    fun restart() {
        gameover = false
        ox = true
        _status.value = "Playing..."
        _data.value = Array(9){_->' '}
    }



}