<template>
  <v-dialog
    v-model="showDialog"
    max-width="750px"
  >
    <v-card>
      <v-container>
        <v-card-title class="text-h5">Buscar Leilão</v-card-title>
        <v-card-text>
          <v-row>
            <v-col cols="12">
              <v-text-field
                label="Nome do leilão"
                v-model="descricao"
                clearable
                outlined
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                v-model="codigo"
                label="Código"
                clearable
                outlined
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                v-model="vendedor"
                label="E-mail"
                clearable
                outlined
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                v-mask="'(##) #####-####'"
                v-model="inicioPrevisto"
                label="Início Previsto"
                clearable
                outlined
              />
            </v-col>
          </v-row>
        </v-card-text>
        <v-card-actions>
          <v-btn
            rounded
            outlined
            color="error"
            @click="onClose"
          >
            <v-icon small>mdi-close</v-icon>
            Cancelar
          </v-btn>
          <v-spacer></v-spacer>
          <v-btn
            rounded
            outlined
            color="success"
            @click="onSearch"
            >
            <v-icon small>mdi-magnify</v-icon>
            Buscar
          </v-btn>
        </v-card-actions>
      </v-container>
    </v-card>
  </v-dialog>
</template>

<script>
import deleteEmptyValues from '../../util/prepareObject';

export default {
  name: 'BuscarLeilao',

  data() {
    return {
      codigo: '',
      vendedor: '',
      inicioPrevisto: '',
      descricao: '',
      deleteEmptyValues,
    };
  },

  props: {
    showDialog: {
      type: Boolean,
      default: false,
      required: true,
    }
  },

  methods: {
    onClose() {
      this.clearFields();
      this.$emit('closeDialog');
    },

    onSearch() {
      const params = this.deleteEmptyValues({
        codigo: this.codigo,
        vendedor: this.vendedor,
        inicioPrevisto: this.inicioPrevisto,
        descricao: this.descricao,
      });
      this.$router.push({
        query: params,
      })
      .then(() => {
        this.clearFields();
        this.$emit('onSearch');
      });
    },

    clearFields() {
      this.codigo = '';
      this.vendedor = '';
      this.descricao = '';
      this.inicioPrevisto = '';
    },
  },
};
</script>

<style>

</style>