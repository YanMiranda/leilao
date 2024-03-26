<template>
  <v-container fluid>
    <v-card elevation="3" :key="key">
      <p
        align="center"
        class="text-h4"
        style="padding-top: 15px; color: #138496;"
      >
        {{ title }}
      </p>
      <v-form v-model="valid">
        <v-container>
          <div class="row">
            <v-col cols="12">
              <v-text-field
                v-model="empresa.razaoSocial"
                :rules="inputRules"
                label="Nome da empresa"
                maxlength="64"
                :counter="64"
                clearable
                outlined
                required
              />
            </v-col>

            <v-col cols="12" md="6">
              <v-text-field
                v-mask="'##.###.###/####-##'"
                v-model="empresa.cnpj"
                :rules="cnpjRules"
                maxlength="32"
                :counter="32"
                label="CNPJ"
                clearable
                outlined
                required
              />
            </v-col>

            <v-col cols="12" md="6">
              <v-text-field
                v-model="empresa.email"
                :rules="emailRules"
                label="E-mail"
                clearable
                outlined
                required
              />
            </v-col>

            <v-col cols="12" md="4">
              <v-text-field
                v-model="empresa.telefone"
                v-mask="'(##) #####-####'"
                label="Telefone (DDD) xxxxx-xxxx"
                clearable
                outlined
              />
            </v-col>

              <v-col cols="12" md="4">
              <v-text-field
                v-model="empresa.numero"
                label="Numero"
                clearable
                outlined
              />
            </v-col>

            <v-col cols="12" md="4">
              <v-text-field
                v-model="empresa.logradouro"
                label="Logradouro"
                clearable
                outlined
                counter
              />
            </v-col>

            <v-col cols="12" md="3">
              <v-text-field
                v-model="empresa.cep"
                v-mask="'#####-###'"
                label="CEP"
                clearable
                outlined
              />
            </v-col>

              <v-col cols="12" md="4">
                <v-text-field
                  v-model="empresa.bairro"
                  label="Bairro"
                  clearable
                  outlined
                />
            </v-col>

            <v-col cols="12" md="5">
              <v-text-field
                v-model="empresa.municipio"
                label="Municipio"
                clearable
                outlined
              />
            </v-col>

            <v-col cols="12" md="4">
              <v-text-field
                v-model="empresa.usuario"
                label="Nome de usuário"
                :rules="inputRules"
                :counter="20"
                clearable
                outlined
                required
              />
            </v-col>

            <v-col cols="12" md="4">
              <v-text-field
                v-model="empresa.senha"
                :append-icon="showPassword ? 'mdi-eye' : 'mdi-eye-off'"
                :type="showPassword ? 'text' : 'password'"
                label="Senha"
                outlined
                counter
                @click:append="showPassword = !showPassword"
              />
            </v-col>

            <v-col cols="12" md="4">
              <v-text-field
                v-model="empresa.site"
                label="Site"
                clearable
                outlined
                counter
              />
            </v-col>
          </div>
          <v-card-actions class="mt-3">
            <v-btn
              outlined
              elevation="2"
              color="error"
              @click="onClickCancelar"
            >
              <v-icon small>mdi-close</v-icon>
              Cancelar
            </v-btn>
            <v-spacer></v-spacer>
            <v-btn
              outlined
              elevation="2"
              color="success"
              :disabled="!valid"
              @click="onClickSave"
            >
              <v-icon small>{{ empresa.id ? 'mdi-pencil' : 'mdi-plus' }}</v-icon>
              {{ empresa.id ? 'Editar' : 'Salvar'}}
            </v-btn>
          </v-card-actions>
        </v-container>
      </v-form>
    </v-card>
    <SnackBar ref="SnackBar"/>
  </v-container>
</template>

<script>
import SnackBar from '../../components/exception/SnackBar.vue';

export default {
  name: 'CadastrarEmpresa',

  components: {
    SnackBar,
  },

  data() {
    return {
      key: 0,
      valid: false,
      showPassword: false,
      empresa: this.resentItem(),
      cnpjRules: [
        (v) => !!v || 'CNPJ é obrigatório',
        (v) => /^\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}$/.test(v) || 'CNPJ deve ser válido',
      ],
      emailRules: [
        (v) => !!v || 'E-mail é obrigatório',
        (v) => /.+@.+\..+/.test(v) || 'E-mail deve ser válido',
      ],
      inputRules: [
        (v) => !!v || 'Campo obrigatório',
      ],
    };
  },

  computed: {
    title () {
      return this.$router.history.current.params.id ? 'Editar cadastro de Empresa' : 'Cadastrar Empresa';
    },
  },

  methods: {
    resentItem() {
      return {
        id: null,
        // @NotNull
        cnpj: '',
        // @NotNull
        razaoSocial: '',
        telefone: '',
        // @NotNull
        email: '',
        logradouro: '',
        numero: '',
        bairro: '',
        municipio: '',
        complemento: '',
        cep: '',
        site: '',
        senha: '',
        // @NotNull
        usuario: '',
      };
    },

    async editForm(id) {
      await this.$axios.get(`/empresas/${id}`)
        .then((response) => {
          this.empresa = response.data;
          this.key += 1;
        })
        .catch((error) => {
          this.$refs.SnackBar.init(error.response.data[0].mensagem, 'error');
        });
    },

    async save(obj) {
      await this.$axios.post('/empresas', obj)
        .then(() => {
          this.$router.push('/empresas');
        })
        .catch((error) => {
          this.$refs.SnackBar.init(error.response.data[0].mensagem, 'error');
        });
    },

    onClickSave() {
      if (this.valid) {
        const sendObj = {
          ...this.empresa,
          cnpj: this.empresa.cnpj.replace(/[^\d]/g, ''),
          cep: this.empresa.cep.replace(/[^\d]/g, ''),
          telefone: this.empresa.telefone.replace(/[^\d]/g, ''),
        };
        if (!this.empresa.id) {
          this.save(sendObj);
        } else {
          this.update(sendObj);
        }
      }

    },

    async update(obj) {
      await this.$axios.put(`/empresas/${obj.id}`, obj)
        .then(() => {
          this.$router.push('/empresas');
        })
        .catch((error) => {
          this.$refs.SnackBar.init(error.response.data[0].mensagem, 'error');
        });
    },

    onClickCancelar() {
      this.resentItem();
      this.$router.push('/empresas');
    },
  },

  mounted() {
    if(this.$route.params.id !== undefined) {
      this.editForm(this.$route.params.id);
    }
  },
};
</script>

<style scoped>

</style>