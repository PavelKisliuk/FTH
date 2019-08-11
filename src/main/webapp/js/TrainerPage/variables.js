var profile = document.querySelector(".profile");
var trainings = document.querySelector(".trainings");
var popupProfile = document.querySelector(".modal-content-profile");
var popupTrainings = document.querySelector(".modal-content-trainings");
var overlay = document.querySelector(".modal-overlay");
var closeProfile = document.querySelector(".modal-content-profile-close");
var closeTrainings = document.querySelector(".modal-content-trainings-close");
var closeDelete = document.querySelector(".modal-content-delete-close");
var closeGive = document.querySelector(".modal-content-give-close");
var closeExtend = document.querySelector(".modal-content-extend-close");
var settings = document.querySelector(".settings");
var settingsButton = document.querySelector(".settings-button");
var actionsLanguage = document.querySelector(".actions__language");
var language = document.querySelector(".language");
var clientList = document.querySelector(".client-base__list");
var sort = document.querySelector(".sort-name");
var sortWrapper = document.querySelector(".sort-wrapper");
var sortWrapperName = document.querySelector(".sort-wrapper__name");
var popupDelete = document.querySelector(".modal-content-delete");
var cancelButton = document.querySelector(".cancel-button");
var popupGive = document.querySelector(".modal-content-give");
var popupExtend = document.querySelector(".modal-content-extend");
var russian = document.querySelector(".russian");
var english = document.querySelector(".english");
var languageChoice = document.querySelector(".language__choice");
var actionsLanguage = document.querySelector(".actions__language");

russian.addEventListener("click", function(event) {
  if (actionsLanguage.classList.contains("actions__language--usa")) {
    actionsLanguage.classList.remove("actions__language--usa");
  }
});

english.addEventListener("click", function(event) {
    actionsLanguage.classList.add("actions__language--usa");
});

sort.addEventListener("click", function(event) {
  if (sortWrapper.classList.contains("active--flex") || sortWrapperName.classList.contains("active--flex")) {
    sortWrapper.classList.remove("active--flex");
    sortWrapperName.classList.remove("active--flex");
    clientList.classList.remove("client-base__list--sort");
  } else {
    sortWrapper.classList.add("active--flex");
    sortWrapperName.classList.add("active--flex");
    clientList.classList.add("client-base__list--sort");
  }
});

settingsButton.addEventListener("click", function(event) {
  if (settings.classList.contains("active")) {
    settings.classList.remove("active");
  } else {
    settings.classList.add("active");
  }
});

actionsLanguage.addEventListener("click", function(event) {
  if (language.classList.contains("active")) {
    language.classList.remove("active");
  } else {
    language.classList.add("active");
  }
});

profile.addEventListener("click", function(event) {
  event.preventDefault();
  popupProfile.classList.add("modal-content-show");
  overlay.classList.add("modal-overlay-show");
});

trainings.addEventListener("click", function(event) {
  event.preventDefault();
  popupTrainings.classList.add("modal-content-show");
  overlay.classList.add("modal-overlay-show");
});

closeProfile.addEventListener("click", function(event) {
  event.preventDefault();
  popupProfile.classList.remove("modal-content-show");
  overlay.classList.remove("modal-overlay-show");
});

closeTrainings.addEventListener("click", function(event) {
  event.preventDefault();
  popupTrainings.classList.remove("modal-content-show");
  overlay.classList.remove("modal-overlay-show");
});

closeDelete.addEventListener("click", function(event) {
  event.preventDefault();
  popupDelete.classList.remove("modal-content-show");
  overlay.classList.remove("modal-overlay-show");
});

closeGive.addEventListener("click", function(event) {
  event.preventDefault();
  popupGive.classList.remove("modal-content-show--flex");
  overlay.classList.remove("modal-overlay-show");
});

closeExtend.addEventListener("click", function(event) {
  event.preventDefault();
  popupExtend.classList.remove("modal-content-show--flex");
  overlay.classList.remove("modal-overlay-show");
});

cancelButton.addEventListener("click", function(event) {
  event.preventDefault();
  popupDelete.classList.remove("modal-content-show");
  overlay.classList.remove("modal-overlay-show");
});

window.addEventListener("keydown", function(event) {
  if (event.keyCode === 27) {
    if (popupProfile.classList.contains("modal-content-show")
    && overlay.classList.contains("modal-overlay-show")){
      popupProfile.classList.remove("modal-content-show");
      overlay.classList.remove("modal-overlay-show");
    }
  }
});

window.addEventListener("keydown", function(event) {
  if (event.keyCode === 27) {
    if (popupTrainings.classList.contains("modal-content-show")
    && overlay.classList.contains("modal-overlay-show")){
      popupTrainings.classList.remove("modal-content-show");
      overlay.classList.remove("modal-overlay-show");
    }
  }
});

window.addEventListener("keydown", function(event) {
  if (event.keyCode === 27) {
    if (popupDelete.classList.contains("modal-content-show")
    && overlay.classList.contains("modal-overlay-show")){
      popupDelete.classList.remove("modal-content-show");
      overlay.classList.remove("modal-overlay-show");
    }
  }
});

window.addEventListener("keydown", function(event) {
  if (event.keyCode === 27) {
    if (popupGive.classList.contains("modal-content-show--flex")
    && overlay.classList.contains("modal-overlay-show")){
      popupGive.classList.remove("modal-content-show--flex");
      overlay.classList.remove("modal-overlay-show");
    }
  }
});

window.addEventListener("keydown", function(event) {
  if (event.keyCode === 27) {
    if (popupExtend.classList.contains("modal-content-show--flex")
    && overlay.classList.contains("modal-overlay-show")){
      popupExtend.classList.remove("modal-content-show--flex");
      overlay.classList.remove("modal-overlay-show");
    }
  }
});

$(function () {
  $(".client-base__list").on("click", ".extend-sub", function () {
    popupExtend.classList.add("modal-content-show--flex");
    overlay.classList.add("modal-overlay-show");
  });

  $(".client-base__list").on("click", ".give-train", function () {
    popupGive.classList.add("modal-content-show--flex");
    overlay.classList.add("modal-overlay-show");
  });

  $(".client-base__list").on("click", ".delete-user", function () {
      $(".someButton").click(function () {
          document.querySelector(".customModal").classList.add("modal-content-show");
          document.querySelector(".modal-overlay").classList.add("modal-overlay-show");
      });

    popupDelete.classList.add("modal-content-show");
    overlay.classList.add("modal-overlay-show");
  });
});
