package com.task.movieapp.data.db.mappers

import com.task.movieapp.data.db.entities.MovieDb
import com.task.movieapp.domain.movie.model.Movie

object MovieMapperDb : Mapper<Movie, MovieDb> {

    fun toEntities(models: List<Movie>): List<MovieDb> {
        val entities = arrayListOf<MovieDb>()
        models.forEach {
            entities.add(toEntity(it))
        }
        return entities
    }

    fun fromEntities(entities: List<MovieDb>): List<Movie> {
        val models = arrayListOf<Movie>()
        entities.forEach {
            models.add(fromEntity(it))
        }
        return models
    }

    override fun toEntity(model: Movie): MovieDb {
        return MovieDb(
            id = model.id,
            title = model.title,
            adult = model.adult,
            backdropPath = model.backdropPath,
            genres = model.genres,
            originalLanguage = model.originalLanguage,
            originalTitle = model.originalTitle,
            overview = model.overview,
            posterPath = model.posterPath,
            releaseDate = model.releaseDate,
            rating = model.rating,
            voteCount = model.voteCount,
            budget = model.budget,
            productionCountries = model.productionCountries,
            productionCompanies = model.productionCompanies,
            revenue = model.revenue,
            spokenLanguages = model.spokenLanguages,
            status = model.status,
            tagLine = model.tagLine,
            duration = model.duration
        )
    }

    override fun fromEntity(entity: MovieDb): Movie {
        return Movie(
            id = entity.id,
            title = entity.title,
            adult = entity.adult,
            backdropPath = entity.backdropPath,
            genres = entity.genres,
            originalLanguage = entity.originalLanguage,
            originalTitle = entity.originalTitle,
            overview = entity.overview,
            posterPath = entity.posterPath,
            releaseDate = entity.releaseDate,
            rating = entity.rating,
            voteCount = entity.voteCount,
            budget = entity.budget,
            productionCountries = entity.productionCountries,
            productionCompanies = entity.productionCompanies,
            revenue = entity.revenue,
            spokenLanguages = entity.spokenLanguages,
            status = entity.status,
            tagLine = entity.tagLine,
            duration = entity.duration
        )
    }
}