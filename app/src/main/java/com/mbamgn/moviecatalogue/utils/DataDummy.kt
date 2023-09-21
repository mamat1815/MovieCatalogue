package com.mbamgn.moviecatalogue.utils

import com.mbamgn.moviecatalogue.data.DataItem
import com.mbamgn.moviecatalogue.data.MovieEntity
import com.mbamgn.moviecatalogue.data.TvShowEntity

object DataDummy {

    fun generateDummyFavMovie(): List<MovieEntity> {
        val movie = ArrayList<MovieEntity>()

        movie.add(
            MovieEntity(
                634649,
                "Spider-Man: No Way Home",
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "/iQFcwSGbZXMkeyKrxbPnwnRo5fl.jpg",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "The Multiverse unleashed.",
                8.5F,
                "2021-12-15",
            )
        )

        movie.add(
            MovieEntity(
                524434,
                "Eternals",
                "/bcCBq9N1EMo3daNIjWJ8kYvrQm6.jpg",
                "/qBLEWvJNVsehJkEJqIigPsWyBse.jpg",
                "The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
                "In the beginning...",
                7.3F,
                "2021-11-03",
            )
        )
        return movie
    }

    fun generateDummyMovie(): List<DataItem> {
        val movie = ArrayList<DataItem>()

        movie.add(
            DataItem(
                634649,
                "Spider-Man: No Way Home",
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "/iQFcwSGbZXMkeyKrxbPnwnRo5fl.jpg",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "The Multiverse unleashed.",
                8.5F,
                "2021-12-15",
                null,
                null,
            )
        )

        movie.add(
            DataItem(
                524434,
                "Eternals",
                "/bcCBq9N1EMo3daNIjWJ8kYvrQm6.jpg",
                "/qBLEWvJNVsehJkEJqIigPsWyBse.jpg",
                "The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
                "In the beginning...",
                7.3F,
                "2021-11-03",
                null,
                null,
            )
        )
        return movie
    }

    fun generateDummyTvShow(): List<DataItem> {
        val tvShow = ArrayList<DataItem>()

        tvShow.add(
            DataItem(
                99966,
                null,
                "/ze4lhw0oLBHYmlM2KuZjBg0Sq6H.jpg",
                "/8Xs20y8gFR0W9u8Yy9NKdpZtSu7.jpg",
                "A high school becomes ground zero for a zombie virus outbreak. Trapped students must fight their way out — or turn into one of the rabid infected.",
                "Hope is the most painful torture for people who want to despair.",
                8.7F,
                null,
                "All of Us Are Dead",
                "2022-01-28",
            )
        )

        tvShow.add(
            DataItem(
                110492,
                null,
                "/hE3LRZAY84fG19a18pzpkZERjTE.jpg",
                "/ctxm191q5o3axFzQsvNPlbKoSYv.jpg",
                "The continuing story of Peacemaker – a compellingly vainglorious man who believes in peace at any cost, no matter how many people he has to kill to get it – in the aftermath of the events of “The Suicide Squad.”",
                "Remember this feeling.",
                8.7F,
                null,
                "Peacemaker",
                "2022-01-13",
            )
        )
        return tvShow
    }

    fun generateDummyFavTvShow(): List<TvShowEntity> {
        val tvShow = ArrayList<TvShowEntity>()
        tvShow.add(
            TvShowEntity(
                99966,
                "All of Us Are Dead",
                "/ze4lhw0oLBHYmlM2KuZjBg0Sq6H.jpg",
                "/8Xs20y8gFR0W9u8Yy9NKdpZtSu7.jpg",
                "A high school becomes ground zero for a zombie virus outbreak. Trapped students must fight their way out — or turn into one of the rabid infected.",
                "Hope is the most painful torture for people who want to despair.",
                8.7F,

                "2022-01-28",
            )
        )

        tvShow.add(
            TvShowEntity(
                110492,
                "Peacemaker",
                "/hE3LRZAY84fG19a18pzpkZERjTE.jpg",
                "/ctxm191q5o3axFzQsvNPlbKoSYv.jpg",
                "The continuing story of Peacemaker – a compellingly vainglorious man who believes in peace at any cost, no matter how many people he has to kill to get it – in the aftermath of the events of “The Suicide Squad.”",
                "Remember this feeling.",
                8.7F,
                "2022-01-13",
            )
        )
        return tvShow

    }

}