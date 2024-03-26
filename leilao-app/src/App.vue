<template>
  <div id="app">
    <v-app>
      <v-main>
        <!-- Barra de navegação -->
        <NavBar/>
        <!-- Grid de opções -->
        <HomeGrid/>
        <router-view/>
      </v-main>
      <v-footer padless>
        <v-col
          class="text-center"
          cols="12"
        >
          {{ new Date().getFullYear() }} — <strong>Projeto Leilão</strong>
        </v-col>
      </v-footer>
    </v-app>

  </div>
</template>

<script>
import NavBar from '@/components/home/NavBar.vue';
import HomeGrid from '@/components/home/HomeGrid.vue';

export default {
  name: 'App',

  components: {
    NavBar,
    HomeGrid,
  },
};
</script>

<style>
</style>
