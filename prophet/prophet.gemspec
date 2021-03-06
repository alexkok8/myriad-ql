# coding: utf-8
lib = File.expand_path('../lib', __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)
require 'prophet/version'

Gem::Specification.new do |spec|
  spec.name          = "prophet"
  spec.version       = Prophet::VERSION
  spec.authors       = ["Andrei Horak"]
  spec.email         = ["linkyndy@gmail.com"]

  spec.summary       = %q{Interpreter for a custom DSL that launches an interactive GUI}
  spec.description   = %q{Prophet interprets a custom-tailored questionnaire DSL and launches an interactive GUI. It is a toy project implemented to showcase the steps of writing an interpreter using beautiful and idiomatic Ruby code.}
  spec.homepage      = "http://github.com/linkyndy/prophet"

  # Prevent pushing this gem to RubyGems.org. To allow pushes either set the 'allowed_push_host'
  # to allow pushing to a single host or delete this section to allow pushing to any host.
  if spec.respond_to?(:metadata)
    spec.metadata['allowed_push_host'] = "TODO: Set to 'http://mygemserver.com'"
  else
    raise "RubyGems 2.0 or newer is required to protect against " \
      "public gem pushes."
  end

  spec.files         = `git ls-files -z`.split("\x0")
  spec.require_paths = ["lib"]

  spec.add_dependency "parslet"
  spec.add_dependency "tk"
  spec.add_dependency "activesupport"

  spec.add_development_dependency "bundler", "~> 1.14"
  spec.add_development_dependency "rake", "~> 10.0"
  spec.add_development_dependency "rspec", "~> 3.0"
  spec.add_development_dependency "awesome_print"
  spec.add_development_dependency "byebug"
  spec.add_development_dependency "rubocop"
  spec.add_development_dependency "reek"
end
