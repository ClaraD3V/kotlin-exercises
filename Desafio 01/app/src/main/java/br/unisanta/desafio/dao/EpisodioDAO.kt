package br.unisanta.desafio.dao

import br.unisanta.desafio.model.Episodio
import android.media.AudioManager
import android.media.MediaPlayer
import android.widget.Toast

class EpisodioDAO {
    companion object {
        private val episodios = mutableListOf<Episodio>()
        private var episodioAtual:Episodio? = null
        private var mediaPlayer:MediaPlayer? = null

        fun excluirEpisodio(episodio: Episodio){
            if (episodioAtual == episodio){
                mediaPlayer?.stop()
                mediaPlayer?.release()
                mediaPlayer = null
                episodioAtual = null
            }
            Companion.episodios.remove(episodio)

        }

        fun reproduzirEpisodio(episodio:Episodio){
            if (episodioAtual == episodio && mediaPlayer?.isPlaying == true){
                mediaPlayer?.pause()
                return
            }
            mediaPlayer?.stop()
            mediaPlayer?.release()
            mediaPlayer = MediaPlayer()
            val url = episodio.url
            mediaPlayer?.setDataSource(url)
            mediaPlayer?.prepare()
            mediaPlayer?.start()
            episodioAtual = episodio
        }

        fun adicionarEpisodio(episodio: Episodio){
            Companion.episodios.add(episodio)
        }

        fun obterEpisodios(): List<Episodio>{
            return Companion.episodios
        }
    }
}