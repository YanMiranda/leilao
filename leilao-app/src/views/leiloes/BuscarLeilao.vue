<template>
  <v-dialog
    v-model="showDialog"
    max-width="750px"
    persistent
  >
    <v-card>
      <v-container>
        <v-card-title class="text-h5">Buscar Leilão</v-card-title>
        <v-card-text>
          <v-row>
            <v-col cols="12">
              <v-text-field
                prepend-icon="mdi-rename-box-outline"
                label="Nome do leilão"
                v-model="descricao"
                clearable
                outlined
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                prepend-icon="mdi-format-color-highlight"
                v-model="codigo"
                label="Código"
                clearable
                outlined
              />
            </v-col>
            <v-col cols="12">
              <v-text-field
                prepend-icon="mdi-account-search"
                v-model="vendedor"
                label="E-mail"
                clearable
                outlined
              />
            </v-col>
            <v-col cols="12">
              <v-menu
                offset-y
                ref="menu"
                v-model="menu"
                max-width="290px"
                min-width="auto"
                transition="scale-transition"
                :close-on-content-click="false"
              >
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field
                    v-on="on"
                    outlined
                    v-bind="attrs"
                    v-model="dateFormated"
                    label="Inicio previsto"
                    prepend-icon="mdi-calendar"
                    hint="Selecione a data de início do leilão"
                  />
                </template>
                <v-date-picker
                  no-title
                  format="dd/MM/yyyy"
                  v-model="dataPrevista"
                  @input="menu = false"
                />
              </v-menu>
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
import { formatDateToRequest, formatdate } from '../../util/DateUtil';

export default {
  name: 'BuscarLeilao',

  data() {
    return {
      codigo: '',
      menu: false,
      vendedor: '',
      descricao: '',
      dataPrevista: '',
      inicioPrevisto: '',
      deleteEmptyValues,
      formatDateToRequest,
      formatdate,
    };
  },

  props: {
    showDialog: {
      type: Boolean,
      default: false,
      required: true,
    }
  },
  computed: {
  dateFormated() {
      return this.dataPrevista ? formatdate(this.dataPrevista) : '';
    }
  },

  watch: {
    'dataPrevista': function (val) {
      formatdate(val);
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
        inicioPrevisto: formatDateToRequest(this.dataPrevista),
        descricao: this.descricao,
      });
      this.$router.push({
        query: params,
      })
      .then(() => {
        this.clearFields();
        this.$emit('onSearch');
      })
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