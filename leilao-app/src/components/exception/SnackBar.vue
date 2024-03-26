<template>
    <v-snackbar
      v-model="snackbar"
      elevation="24"
      :color="cor"
      :timeout="2000"
      centered
      top
      @close="snackbar = false"
    >
      <div class="d-flex justify-content-between align-items-center">
        <strong>{{ mensagem }}</strong>
        <v-btn
          text
          small
          @click="snackbar = false"
        >
          <v-icon small> mdi-close</v-icon>
        </v-btn>
      </div>
    </v-snackbar>
</template>

<script>
export default {
  name: 'Snackbar',

  data() {
    return {
      snackbar: false,
      mensagem: '',
      cor: 'success',
    };
  },

  methods: {
    init(msg, cor) {
      console.log('init');
      this.snackbar = true;
      this.mensagem = msg;
      this.cor = cor;
    },
  },
};
</script>
