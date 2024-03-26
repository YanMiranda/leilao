<template>
  <v-app-bar
    app
    dense
    rounded
    color="#8daec3"
  >
    <img src="../../assets/logo.jpeg" alt="Logo Leilão" :class="(isMobile) ? 'logo-mobile' : 'logo-desktop'"/>
    <router-link to="/" class="navbar-brand" style="color: #0F1D38;">Leilão</router-link>

    <v-spacer></v-spacer>

    <v-btn
      text
      :x-small="isMobile"
      to="/unidades"
      class="nav-item"
    >
      Unidades
    </v-btn>
    <v-btn
      text
      :x-small="isMobile"
      to="/empresas"
      class="nav-item"
    >
      Empresas
    </v-btn>
    <v-btn
      text
      :x-small="isMobile"
      to="/leiloes"
      class="nav-item"
    >
      Leilões
    </v-btn>
  </v-app-bar>
</template>

<script>
export default {
  name: 'NavBar',

}
</script>

<style>

/* Estilos para o logo */
.logo-desktop {
  width: 40px;
  height: 40px;
  margin-right: 10px;
  margin-left: 4px;
  margin-top: 3px;
  border-radius: 50%;
}

.logo-mobile {
  width: 30px;
  height: 30px;
  margin-right: 10px;
  border-radius: 50%;
  margin-top: 8px;
  margin-left: 4px;
}
</style>