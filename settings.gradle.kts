rootProject.name = "Genemis"

include("backend:version-catalog")
include("backend:dispatcher")

dependencyResolutionManagement {

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }

    versionCatalogs {
        create("libs") {
            from(files("backend/version-catalog/catalog/libs.versions.toml"))
        }
    }
}


