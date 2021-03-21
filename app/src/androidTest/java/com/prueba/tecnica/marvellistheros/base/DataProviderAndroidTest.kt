package com.prueba.tecnica.marvellistheros.base

import com.prueba.tecnica.marvellistheros.features.characters.data.model.CharacterDto
import com.prueba.tecnica.marvellistheros.features.characters.data.model.CharacterListDto
import com.prueba.tecnica.marvellistheros.features.characters.data.model.MarvelCharacterListDto
import com.prueba.tecnica.marvellistheros.features.characters.domain.model.Character
import com.prueba.tecnica.marvellistheros.features.characters.domain.model.CharacterList
import com.prueba.tecnica.marvellistheros.features.characters.domain.model.MarvelCharacterList
import com.prueba.tecnica.marvellistheros.features.commons.data.model.FavoriteDto
import com.prueba.tecnica.marvellistheros.features.commons.domain.model.Favorite
import com.prueba.tecnica.marvellistheros.features.details.data.model.*
import com.prueba.tecnica.marvellistheros.features.details.domain.model.*

object DataProviderAndroidTest {

    const val mockApiKey = ""
    const val mockHash = ""
    const val mockTimestamp = 0L

    const val mockPath = "http://i.annihil.us/u/prod/marvel/i/mg/3/40/4bb4680432f73/portrait_xlarge"
    const val mockExtension = ".jpg"

    const val mockCharacterId = 1L
    const val mockCharacterName = "Character Name"
    const val mockCharacterUrl = "Character Url"

    const val mockOffset = 0
    const val mockLimit = 20
    const val mockTotal = 100
    const val mockCount = 20

    const val mockFavoriteId = 1L
    const val mockFavoriteName = ""
    const val mockFavoriteUrl = ""

    const val mockCharacterDetailId = 1L
    const val mockCharacterDetailName = ""
    const val mockCharacterDetailDescription = ""
    const val mockCharacterDetailUrl = 1L

    const val mockDetailInfoId = 1L
    const val mockDetailInfoTitle = ""

    // region Data
    fun createCharacterListDto(): CharacterListDto {
        return CharacterListDto(mockOffset, mockLimit, mockTotal, mockCount, createCharactersDto())
    }

    fun createCharactersDto(): List<CharacterDto> {
        return listOf(
            createCharacterDto(),
            createCharacterDto(),
            createCharacterDto()
        )
    }

    fun createCharacterDto(
        id: Long? = mockCharacterId,
        name: String? = mockCharacterName,
        path: String? = mockPath,
        extension: String? = mockExtension
    ): CharacterDto {
        return CharacterDto(
            id,
            name,
            createThumbnailDtoCharacter(path, extension)
        )
    }

    fun createThumbnailDtoCharacter(
        path: String? = mockPath,
        extension: String? = mockExtension
    ): com.prueba.tecnica.marvellistheros.features.characters.data.model.ThumbnailDto {
        return com.prueba.tecnica.marvellistheros.features.characters.data.model.ThumbnailDto(path, extension)
    }

    fun createThumbnailDtoDetails(
        path: String? = mockPath,
        extension: String? = mockExtension
    ): com.prueba.tecnica.marvellistheros.features.details.data.model.ThumbnailDto {
        return com.prueba.tecnica.marvellistheros.features.details.data.model.ThumbnailDto(
            path,
            extension
        )
    }

    fun createMarvelCharacterListDto(): MarvelCharacterListDto {
        return MarvelCharacterListDto(createCharacterListDto())
    }

    fun createFavoriteDto(
        favoriteId: Long = mockFavoriteId,
        favoriteName: String = mockFavoriteName,
        favoriteUrl: String = mockFavoriteUrl
    ): FavoriteDto {
        return FavoriteDto(favoriteId, favoriteName, favoriteUrl)
    }

    fun createCharacterDetailDto(): CharacterDetailDto {
        return CharacterDetailDto(
            mockCharacterDetailId,
            mockCharacterDetailName,
            mockCharacterDetailDescription,
            createThumbnailDtoDetails()
        )
    }

    fun createCharacterDetailListDto(): CharacterDetailListDto {
        return CharacterDetailListDto(
            mockOffset,
            mockLimit,
            mockTotal,
            mockCount,
            listOf(createCharacterDetailDto(), createCharacterDetailDto(), createCharacterDetailDto())
        )
    }

    fun createDetailInfoDto(): DetailInfoDto {
        return DetailInfoDto(
            mockDetailInfoId,
            mockDetailInfoTitle,
            createThumbnailDtoDetails()
        )
    }

    fun createDetailInfoListDto(): DetailInfoListDto {
        return DetailInfoListDto(
            mockOffset,
            mockLimit,
            mockTotal,
            mockCount,
            listOf(createDetailInfoDto(), createDetailInfoDto(), createDetailInfoDto())
        )
    }

    fun createMarvelCharacterDetailDto(): MarvelCharacterDetailDto {
        return MarvelCharacterDetailDto(
            createCharacterDetailListDto()
        )
    }

    fun createMarvelDetailInfoDto(): MarvelDetailInfoDto {
        return MarvelDetailInfoDto(
            createDetailInfoListDto()
        )
    }

    // endregion

    // region Domain
    fun createCharacterList(): CharacterList {
        return CharacterList(mockOffset, mockLimit, mockTotal, mockCount, createCharacters())
    }

    fun createCharacters(): List<Character> {
        return listOf(
            createCharacter(),
            createCharacter(),
            createCharacter()
        )
    }

    fun createCharacter(
        id: Long = mockCharacterId,
        name: String = mockCharacterName,
        path: String = mockPath,
        extension: String = mockExtension
    ): com.prueba.tecnica.marvellistheros.features.characters.domain.model.Character {
        return com.prueba.tecnica.marvellistheros.features.characters.domain.model.Character(
            id,
            name,
            createThumbnailCharacter(path, extension)
        )
    }

    fun createThumbnailCharacter(
        path: String = mockPath,
        extension: String = mockExtension
    ): com.prueba.tecnica.marvellistheros.features.characters.domain.model.Thumbnail {
        return com.prueba.tecnica.marvellistheros.features.characters.domain.model.Thumbnail(path, extension)
    }

    fun createThumbnailDetails(
        path: String = mockPath,
        extension: String = mockExtension
    ): com.prueba.tecnica.marvellistheros.features.details.domain.model.Thumbnail {
        return com.prueba.tecnica.marvellistheros.features.details.domain.model.Thumbnail(path, extension)
    }

    fun createMarvelCharacterList(): MarvelCharacterList {
        return MarvelCharacterList(createCharacterList())
    }

    fun createFavorite(
        favoriteId: Long = mockFavoriteId,
        favoriteName: String = mockFavoriteName,
        favoriteUrl: String = mockFavoriteUrl
    ): Favorite {
        return Favorite(favoriteId, favoriteName, favoriteUrl)
    }

    fun createFavorites(): List<Favorite> {
        return listOf(createFavorite(), createFavorite(), createFavorite())
    }

    fun createCharacterDetail(): CharacterDetail {
        return CharacterDetail(
            mockCharacterDetailId,
            mockCharacterDetailName,
            mockCharacterDetailDescription,
            createThumbnailDetails()
        )
    }

    fun createCharacterDetailList(): CharacterDetailList {
        return CharacterDetailList(
            mockOffset,
            mockLimit,
            mockTotal,
            mockCount,
            listOf(createCharacterDetail(), createCharacterDetail(), createCharacterDetail())
        )
    }

    fun createDetailInfo(): DetailInfo {
        return DetailInfo(
            mockDetailInfoId,
            mockDetailInfoTitle,
            createThumbnailDetails()
        )
    }

    fun createDetailInfoAsList(): List<DetailInfo> {
        return listOf(createDetailInfo(), createDetailInfo(), createDetailInfo())
    }

    fun createDetailInfoList(): DetailInfoList {
        return DetailInfoList(
            mockOffset,
            mockLimit,
            mockTotal,
            mockCount,
            listOf(createDetailInfo(), createDetailInfo(), createDetailInfo())
        )
    }

    fun createMarvelCharacterDetail(): MarvelCharacterDetail {
        return MarvelCharacterDetail(
            createCharacterDetailList()
        )
    }

    fun createMarvelDetailInfo(): MarvelDetailInfo {
        return MarvelDetailInfo(
            createDetailInfoList()
        )
    }
    // endregion
}