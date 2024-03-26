<template>
  <v-dialog
    v-model="showDialog"
    max-width="750px"
    persistent
  >
    <v-card>
      <v-card-title class="text-h5">Buscar Empresa</v-card-title>
      <v-card-text>
        <v-container>
          <v-row>
            <v-col cols="12">
              <v-text-field
                prepend-icon="mdi-rename-box-outline"
                v-model="razaoSocial"
                label="Nome da empresa"
                clearable
                outlined
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                prepend-icon="mdi-id-card"
                v-mask="'##.###.###/####-##'"
                v-model="cnpj"
                label="CNPJ"
                clearable
                outlined
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                prepend-icon="mdi-email-check-outline"
                v-model="email"
                label="E-mail"
                clearable
                outlined
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                prepend-icon="mdi-phone-check-outline"
                v-mask="'(##) #####-####'"
                v-model="telefone"
                label="Telefone"
                clearable
                outlined
              />
            </v-col>
          </v-row>
        </v-container>
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
    </v-card>
  </v-dialog>
</template>

<script>
import deleteEmptyValues from '../../util/prepareObject';

export default {
  name: 'BuscarEmpresa',

  data() {
    return {
      cnpj: '',
      email: '',
      telefone: '',
      razaoSocial: '',
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
        cnpj: this.cnpj.replace(/[^\d]+/g, ''),
        email: this.email,
        telefone: this.telefone.replace(/[^\d]+/g, ''),
        razaoSocial: this.razaoSocial,
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
      this.cnpj = '';
      this.email = '';
      this.telefone = '';
      this.razaoSocial = '';
    },
  },
};
</script>

<style>

</style>