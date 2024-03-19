package dependencies


object RoomDependencies {
    private const val roomVersion = "2.6.0"

    const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
    const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    const val annotationProcessorRoomCompiler = "androidx.room:room-compiler:$roomVersion"
    const val kspRoomCompiler = "androidx.room:room-compiler:$roomVersion"
}

object DataStoreDependencies {
    private const val DATA_STORE_VERSION = "1.0.0"

    const val DATA_STORE = "androidx.datastore:datastore-preferences:$DATA_STORE_VERSION"
    const val PROTO_DATA_STORE = "androidx.datastore:datastore:$DATA_STORE_VERSION"
}
