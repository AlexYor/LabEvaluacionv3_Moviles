package com.flores.yorsh.laboratoriocalificado03

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("list/teacher")
    suspend fun getTeachers(): Response<TeacherListResponse>
}