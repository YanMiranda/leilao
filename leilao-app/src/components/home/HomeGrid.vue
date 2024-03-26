<template>
  <v-container>
    <v-row v-if="isRouteHome">
      <v-col
        v-for="(title, index) in titleGrid"
        :key="index"
        cols="12"
        md="4"
      >
        <v-card
          shaped
          class="menu-card"
          color="#BDCDD4"
          elevation="5"
          @click="navigateTo(routeGrid[index])"
        >
          <v-card-title>{{ title }}</v-card-title>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: 'HomeGrid',

  data() {
    return {
      titleGrid: ['Unidade', 'Empresa', 'Leilões'],
      routeGrid: ['unidades', 'empresas', 'leiloes'],
    };
  },

  computed: {
    isRouteHome() {
      return this.$route.path === '/';
    },
  },

  methods: {
    navigateTo(route) {
      this.$router.push(route);
    },
  },
};
</script>

<style scoped>
.menu-card {
  cursor: pointer;
  margin: 10px;
  padding: 40px;
  text-align: center;
}
</style>
