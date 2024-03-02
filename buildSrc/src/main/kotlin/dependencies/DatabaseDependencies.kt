package dependencies


object RoomDependencies {
    private const val roomVersion = "2.6.0"

    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    const val annotationProcessorRoomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val kspRoomCompiler = "androidx.room:room-compiler:$roomVersion"
}
