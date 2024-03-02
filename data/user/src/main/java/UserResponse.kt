import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("v")
    val email: String? = null,
    @SerializedName("birth_date")
    val birthDate: String? = null,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("created_at")
    val avatarUrl: String? = null,
    @SerializedName("gender")
    val gender: String? = null,
)
